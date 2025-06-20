package com.kraatz.player.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.io.File

/**
 * Manages sharing functionality for the music player
 */
class ShareManager(private val context: Context) {
    
    private val fileManager = FileManager(context)

    /**
     * Share a single audio file
     */
    fun shareAudioFile(file: File) {
        try {
            val shareIntent = fileManager.shareAudioFile(file, "Share Music")
            if (shareIntent != null) {
                context.startActivity(shareIntent)
            } else {
                showError("Unable to share file")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showError("Error sharing file: ${e.message}")
        }
    }

    /**
     * Share multiple audio files
     */
    fun shareMultipleAudioFiles(files: List<File>) {
        try {
            val shareIntent = fileManager.shareMultipleAudioFiles(files, "Share Music Files")
            if (shareIntent != null) {
                context.startActivity(shareIntent)
            } else {
                showError("Unable to share files")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showError("Error sharing files: ${e.message}")
        }
    }

    /**
     * Share current playlist
     */
    fun sharePlaylist(playlistName: String, audioFiles: List<File>) {
        try {
            val playlistFile = fileManager.createPlaylist(playlistName, audioFiles)
            if (playlistFile != null) {
                val uri = fileManager.getShareableUri(playlistFile)
                if (uri != null) {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "audio/x-mpegurl" // M3U playlist MIME type
                        putExtra(Intent.EXTRA_STREAM, uri)
                        putExtra(Intent.EXTRA_SUBJECT, "Playlist: $playlistName")
                        putExtra(Intent.EXTRA_TEXT, "Shared from Kraatz Player")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    
                    val chooser = Intent.createChooser(shareIntent, "Share Playlist")
                    context.startActivity(chooser)
                } else {
                    showError("Unable to create shareable playlist")
                }
            } else {
                showError("Unable to create playlist file")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showError("Error sharing playlist: ${e.message}")
        }
    }

    /**
     * Share app with others
     */
    fun shareApp() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Check out Kraatz Player!")
                putExtra(Intent.EXTRA_TEXT, buildString {
                    appendLine("🎵 Kraatz Player - Your Ultimate Music Experience!")
                    appendLine()
                    appendLine("Features:")
                    appendLine("• High-quality audio playback")
                    appendLine("• Professional 5-band equalizer")
                    appendLine("• Beautiful modern interface")
                    appendLine("• Playlist management")
                    appendLine("• File sharing capabilities")
                    appendLine()
                    appendLine("Download it now and enjoy your music like never before!")
                })
            }
            
            val chooser = Intent.createChooser(shareIntent, "Share Kraatz Player")
            context.startActivity(chooser)
        } catch (e: Exception) {
            e.printStackTrace()
            showError("Error sharing app: ${e.message}")
        }
    }

    /**
     * Share now playing song info
     */
    fun shareNowPlaying(songTitle: String, artist: String, album: String? = null) {
        try {
            val shareText = buildString {
                appendLine("🎵 Now Playing:")
                appendLine("$songTitle")
                if (artist.isNotBlank()) {
                    appendLine("by $artist")
                }
                if (!album.isNullOrBlank()) {
                    appendLine("from $album")
                }
                appendLine()
                appendLine("Shared from Kraatz Player 🎧")
            }
            
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Now Playing: $songTitle")
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            
            val chooser = Intent.createChooser(shareIntent, "Share Now Playing")
            context.startActivity(chooser)
        } catch (e: Exception) {
            e.printStackTrace()
            showError("Error sharing now playing: ${e.message}")
        }
    }

    /**
     * Export and share app data/settings
     */
    fun exportAndShareAppData() {
        try {
            val exportFile = fileManager.exportAppData()
            if (exportFile != null) {
                val uri = fileManager.getShareableUri(exportFile)
                if (uri != null) {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "application/zip"
                        putExtra(Intent.EXTRA_STREAM, uri)
                        putExtra(Intent.EXTRA_SUBJECT, "Kraatz Player Backup")
                        putExtra(Intent.EXTRA_TEXT, "Backup of Kraatz Player data and settings")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    
                    val chooser = Intent.createChooser(shareIntent, "Share App Backup")
                    context.startActivity(chooser)
                } else {
                    showError("Unable to create shareable backup")
                }
            } else {
                showError("Unable to create backup file")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showError("Error exporting app data: ${e.message}")
        }
    }

    /**
     * Handle incoming shared files
     */
    fun handleSharedFiles(intent: Intent): List<File> {
        val sharedFiles = mutableListOf<File>()
        
        try {
            when (intent.action) {
                Intent.ACTION_SEND -> {
                    val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
                    uri?.let { 
                        val file = uriToFile(it)
                        if (file != null && fileManager.isSupportedAudioFile(file)) {
                            sharedFiles.add(file)
                        }
                    }
                }
                Intent.ACTION_SEND_MULTIPLE -> {
                    val uris = intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)
                    uris?.forEach { uri ->
                        val file = uriToFile(uri)
                        if (file != null && fileManager.isSupportedAudioFile(file)) {
                            sharedFiles.add(file)
                        }
                    }
                }
                Intent.ACTION_VIEW -> {
                    val uri = intent.data
                    uri?.let {
                        val file = uriToFile(it)
                        if (file != null && fileManager.isSupportedAudioFile(file)) {
                            sharedFiles.add(file)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showError("Error handling shared files: ${e.message}")
        }
        
        return sharedFiles
    }

    /**
     * Convert URI to File (simplified version)
     */
    private fun uriToFile(uri: Uri): File? {
        return try {
            when (uri.scheme) {
                "file" -> File(uri.path ?: return null)
                "content" -> {
                    // For content URIs, you might need to copy to temp file
                    // This is a simplified version
                    val path = uri.path
                    if (path != null) File(path) else null
                }
                else -> null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Show error message to user
     */
    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Show success message to user
     */
    private fun showSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Composable function to remember ShareManager
 */
@Composable
fun rememberShareManager(): ShareManager {
    val context = LocalContext.current
    return remember { ShareManager(context) }
}

