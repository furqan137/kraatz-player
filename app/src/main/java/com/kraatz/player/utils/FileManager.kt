package com.kraatz.player.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * Utility class for managing file operations, permissions, and sharing
 */
class FileManager(private val context: Context) {

    companion object {
        const val REQUEST_CODE_STORAGE_PERMISSION = 1001
        const val REQUEST_CODE_MANAGE_STORAGE = 1002
        
        // Required permissions for different Android versions
        val STORAGE_PERMISSIONS_LEGACY = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        
        val STORAGE_PERMISSIONS_MODERN = arrayOf(
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_IMAGES
        )
    }

    /**
     * Check if all required storage permissions are granted
     */
    fun hasStoragePermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ (API 33+)
            STORAGE_PERMISSIONS_MODERN.all { permission ->
                ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+ (API 30+)
            Environment.isExternalStorageManager() || 
            STORAGE_PERMISSIONS_LEGACY.all { permission ->
                ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
            }
        } else {
            // Android 10 and below
            STORAGE_PERMISSIONS_LEGACY.all { permission ->
                ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
            }
        }
    }

    /**
     * Get the appropriate permissions to request based on Android version
     */
    fun getRequiredPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            STORAGE_PERMISSIONS_MODERN
        } else {
            STORAGE_PERMISSIONS_LEGACY
        }
    }

    /**
     * Check if we need to request MANAGE_EXTERNAL_STORAGE permission
     */
    fun needsManageStoragePermission(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()
    }

    /**
     * Create intent to request MANAGE_EXTERNAL_STORAGE permission
     */
    fun createManageStorageIntent(): Intent {
        return Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
            data = Uri.parse("package:${context.packageName}")
        }
    }

    /**
     * Get a secure URI for sharing a file using FileProvider
     */
    fun getShareableUri(file: File): Uri? {
        return try {
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Share an audio file with other apps
     */
    fun shareAudioFile(file: File, title: String = "Share Audio"): Intent? {
        val uri = getShareableUri(file) ?: return null
        
        return Intent(Intent.ACTION_SEND).apply {
            type = "audio/*"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, file.nameWithoutExtension)
            putExtra(Intent.EXTRA_TEXT, "Shared from Kraatz Player")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }.let { Intent.createChooser(it, title) }
    }

    /**
     * Share multiple audio files
     */
    fun shareMultipleAudioFiles(files: List<File>, title: String = "Share Audio Files"): Intent? {
        if (files.isEmpty()) return null
        
        val uris = files.mapNotNull { getShareableUri(it) }
        if (uris.isEmpty()) return null
        
        return Intent(Intent.ACTION_SEND_MULTIPLE).apply {
            type = "audio/*"
            putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(uris))
            putExtra(Intent.EXTRA_SUBJECT, "Audio files from Kraatz Player")
            putExtra(Intent.EXTRA_TEXT, "Shared ${files.size} audio files from Kraatz Player")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }.let { Intent.createChooser(it, title) }
    }

    /**
     * Copy a file to app's private storage
     */
    fun copyToPrivateStorage(sourceFile: File, destinationName: String): File? {
        return try {
            val privateDir = File(context.filesDir, "music")
            if (!privateDir.exists()) {
                privateDir.mkdirs()
            }
            
            val destinationFile = File(privateDir, destinationName)
            
            FileInputStream(sourceFile).use { input ->
                FileOutputStream(destinationFile).use { output ->
                    input.copyTo(output)
                }
            }
            
            destinationFile
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Get music directories to scan
     */
    fun getMusicDirectories(): List<File> {
        val directories = mutableListOf<File>()
        
        // Primary external storage music directory
        val primaryMusicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        if (primaryMusicDir.exists()) {
            directories.add(primaryMusicDir)
        }
        
        // Downloads directory (often contains music)
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (downloadsDir.exists()) {
            directories.add(downloadsDir)
        }
        
        // App's external files directory
        context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.let { appMusicDir ->
            if (appMusicDir.exists()) {
                directories.add(appMusicDir)
            }
        }
        
        // Common music directories
        val commonDirs = listOf("Music", "Audio", "Sounds", "Ringtones")
        val externalStorage = Environment.getExternalStorageDirectory()
        
        commonDirs.forEach { dirName ->
            val dir = File(externalStorage, dirName)
            if (dir.exists() && dir.isDirectory) {
                directories.add(dir)
            }
        }
        
        return directories.distinct()
    }

    /**
     * Scan for audio files in given directories
     */
    fun scanForAudioFiles(directories: List<File>): List<File> {
        val audioFiles = mutableListOf<File>()
        val supportedExtensions = setOf("mp3", "wav", "flac", "aac", "ogg", "m4a", "wma")
        
        directories.forEach { directory ->
            try {
                directory.walkTopDown()
                    .filter { it.isFile }
                    .filter { file ->
                        supportedExtensions.contains(file.extension.lowercase())
                    }
                    .forEach { audioFiles.add(it) }
            } catch (e: SecurityException) {
                // Handle permission issues
                e.printStackTrace()
            }
        }
        
        return audioFiles.sortedBy { it.name }
    }

    /**
     * Create a playlist file
     */
    fun createPlaylist(name: String, audioFiles: List<File>): File? {
        return try {
            val playlistsDir = File(context.filesDir, "playlists")
            if (!playlistsDir.exists()) {
                playlistsDir.mkdirs()
            }
            
            val playlistFile = File(playlistsDir, "$name.m3u")
            
            playlistFile.writeText(buildString {
                appendLine("#EXTM3U")
                audioFiles.forEach { file ->
                    appendLine("#EXTINF:-1,${file.nameWithoutExtension}")
                    appendLine(file.absolutePath)
                }
            })
            
            playlistFile
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Export app data (playlists, settings, etc.)
     */
    fun exportAppData(): File? {
        return try {
            val exportDir = File(context.getExternalFilesDir(null), "exports")
            if (!exportDir.exists()) {
                exportDir.mkdirs()
            }
            
            val timestamp = System.currentTimeMillis()
            val exportFile = File(exportDir, "kraatz_player_backup_$timestamp.zip")
            
            // Create a simple backup of important files
            // This is a simplified version - you might want to use a proper zip library
            exportFile.createNewFile()
            exportFile
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Get file size in human readable format
     */
    fun getReadableFileSize(file: File): String {
        val bytes = file.length()
        val units = arrayOf("B", "KB", "MB", "GB")
        var size = bytes.toDouble()
        var unitIndex = 0
        
        while (size >= 1024 && unitIndex < units.size - 1) {
            size /= 1024
            unitIndex++
        }
        
        return "%.1f %s".format(size, units[unitIndex])
    }

    /**
     * Check if file is a supported audio format
     */
    fun isSupportedAudioFile(file: File): Boolean {
        val supportedExtensions = setOf("mp3", "wav", "flac", "aac", "ogg", "m4a", "wma")
        return supportedExtensions.contains(file.extension.lowercase())
    }
}

