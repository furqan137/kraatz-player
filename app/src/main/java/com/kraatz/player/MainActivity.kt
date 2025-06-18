package com.kraatz.player

import android.Manifest
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
// import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.kraatz.player.service.SimpleMusicService
import com.kraatz.player.ui.screens.SimpleMainScreen
import com.kraatz.player.ui.theme.KraatzPlayerTheme
// import dagger.hilt.android.AndroidEntryPoint

// @AndroidEntryPoint - Temporarily disabled
class MainActivity : ComponentActivity() {
    
    private lateinit var controllerFuture: ListenableFuture<MediaController>
    private var mediaController: MediaController? = null
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            // Permissions granted, proceed with music scanning
            onPermissionsGranted()
        } else {
            // Handle permission denial gracefully
            onPermissionsDenied()
        }
    }
    
    private fun onPermissionsGranted() {
        // Initialize media functionality when permissions are granted
        // This can be expanded later for music library scanning
    }
    
    private fun onPermissionsDenied() {
        // App can still function with limited features
        // Show user a message about limited functionality if needed
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        checkAndRequestPermissions()
        initializeMediaController()
        
        setContent {
            KraatzPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SimpleMainScreen()
                }
            }
        }
    }
    
    private fun checkAndRequestPermissions() {
        val permissions = mutableListOf<String>()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) 
                != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_MEDIA_AUDIO)
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) 
                != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        
        if (permissions.isNotEmpty()) {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    }
    
    private fun initializeMediaController() {
        try {
            val sessionToken = SessionToken(this, ComponentName(this, SimpleMusicService::class.java))
            controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
            controllerFuture.addListener({
                try {
                    mediaController = controllerFuture.get()
                    // Successfully connected to media service
                } catch (e: Exception) {
                    // Handle connection failure gracefully
                    e.printStackTrace()
                    mediaController = null
                }
            }, ContextCompat.getMainExecutor(this))
        } catch (e: Exception) {
            // Handle initialization failure gracefully
            e.printStackTrace()
        }
    }
    
    override fun onDestroy() {
        try {
            if (::controllerFuture.isInitialized) {
                MediaController.releaseFuture(controllerFuture)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }
}
