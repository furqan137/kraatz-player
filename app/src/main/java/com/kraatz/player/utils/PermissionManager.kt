package com.kraatz.player.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Manages runtime permissions for the music player app
 */
class PermissionManager(private val activity: ComponentActivity) {
    
    private var onPermissionResult: ((Boolean) -> Unit)? = null
    
    // Permission launchers
    private val storagePermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        onPermissionResult?.invoke(allGranted)
    }
    
    private val manageStorageLauncher = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        val hasPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            true
        }
        onPermissionResult?.invoke(hasPermission)
    }

    /**
     * Check if all required permissions are granted
     */
    fun hasAllPermissions(): Boolean {
        val fileManager = FileManager(activity)
        return fileManager.hasStoragePermissions()
    }

    /**
     * Request storage permissions
     */
    fun requestStoragePermissions(onResult: (Boolean) -> Unit) {
        onPermissionResult = onResult
        
        val fileManager = FileManager(activity)
        
        if (fileManager.hasStoragePermissions()) {
            onResult(true)
            return
        }
        
        // For Android 11+ (API 30+), we might need MANAGE_EXTERNAL_STORAGE
        if (fileManager.needsManageStoragePermission()) {
            requestManageStoragePermission()
        } else {
            // Request regular storage permissions
            val permissions = fileManager.getRequiredPermissions()
            storagePermissionLauncher.launch(permissions)
        }
    }

    /**
     * Request MANAGE_EXTERNAL_STORAGE permission (Android 11+)
     */
    private fun requestManageStoragePermission() {
        val fileManager = FileManager(activity)
        val intent = fileManager.createManageStorageIntent()
        manageStorageLauncher.launch(intent)
    }

    /**
     * Check if we should show rationale for permissions
     */
    fun shouldShowPermissionRationale(): Boolean {
        val fileManager = FileManager(activity)
        val permissions = fileManager.getRequiredPermissions()
        
        return permissions.any { permission ->
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
        }
    }

    /**
     * Open app settings for manual permission grant
     */
    fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:${activity.packageName}")
        }
        activity.startActivity(intent)
    }

    /**
     * Get permission status details
     */
    fun getPermissionStatus(): PermissionStatus {
        val fileManager = FileManager(activity)
        
        return PermissionStatus(
            hasStoragePermission = fileManager.hasStoragePermissions(),
            needsManageStorage = fileManager.needsManageStoragePermission(),
            shouldShowRationale = shouldShowPermissionRationale()
        )
    }
}

/**
 * Data class representing permission status
 */
data class PermissionStatus(
    val hasStoragePermission: Boolean,
    val needsManageStorage: Boolean,
    val shouldShowRationale: Boolean
) {
    val hasAllPermissions: Boolean
        get() = hasStoragePermission && !needsManageStorage
}

/**
 * Composable for handling permissions with UI
 */
@Composable
fun PermissionHandler(
    permissionManager: PermissionManager,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    content: @Composable () -> Unit
) {
    var permissionStatus by remember { mutableStateOf(permissionManager.getPermissionStatus()) }
    var showRationale by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        permissionStatus = permissionManager.getPermissionStatus()
        
        if (!permissionStatus.hasAllPermissions) {
            if (permissionStatus.shouldShowRationale) {
                showRationale = true
            } else {
                permissionManager.requestStoragePermissions { granted ->
                    if (granted) {
                        onPermissionGranted()
                    } else {
                        onPermissionDenied()
                    }
                    permissionStatus = permissionManager.getPermissionStatus()
                }
            }
        } else {
            onPermissionGranted()
        }
    }
    
    if (permissionStatus.hasAllPermissions) {
        content()
    } else {
        // Show permission request UI
        PermissionRequestScreen(
            permissionStatus = permissionStatus,
            showRationale = showRationale,
            onRequestPermissions = {
                showRationale = false
                permissionManager.requestStoragePermissions { granted ->
                    if (granted) {
                        onPermissionGranted()
                    } else {
                        onPermissionDenied()
                    }
                    permissionStatus = permissionManager.getPermissionStatus()
                }
            },
            onOpenSettings = {
                permissionManager.openAppSettings()
            }
        )
    }
}

@Composable
private fun PermissionRequestScreen(
    permissionStatus: PermissionStatus,
    showRationale: Boolean,
    onRequestPermissions: () -> Unit,
    onOpenSettings: () -> Unit
) {
    // This would be implemented with your UI components
    // For now, just trigger the permission request
    LaunchedEffect(showRationale) {
        if (showRationale) {
            onRequestPermissions()
        }
    }
}

