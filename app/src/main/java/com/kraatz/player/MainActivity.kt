package com.kraatz.player

import android.Manifest
import android.content.ComponentName
import android.content.Intent
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.core.content.ContextCompat
// import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.kraatz.player.service.SimpleMusicService
import com.kraatz.player.ui.screens.SimpleMainScreen
import com.kraatz.player.ui.screens.MusicLibraryScreen
import com.kraatz.player.ui.screens.PlayerScreen
import com.kraatz.player.ui.screens.EqualizerScreen
import com.kraatz.player.ui.theme.KraatzPlayerTheme
import com.kraatz.player.ui.viewmodel.MusicPlayerViewModel
import com.kraatz.player.ui.viewmodel.MusicPlayerViewModelFactory
import com.kraatz.player.utils.PermissionManager
import com.kraatz.player.utils.ShareManager
import com.kraatz.player.utils.FileManager
// import dagger.hilt.android.AndroidEntryPoint

// @AndroidEntryPoint - Temporarily disabled
class MainActivity : ComponentActivity() {
    
    private lateinit var controllerFuture: ListenableFuture<MediaController>
    private var mediaController: MediaController? = null
    
    // Permission and sharing managers
    private lateinit var permissionManager: PermissionManager
    private lateinit var shareManager: ShareManager
    private lateinit var fileManager: FileManager
    
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
        
        // Initialize managers
        permissionManager = PermissionManager(this)
        shareManager = ShareManager(this)
        fileManager = FileManager(this)
        
        // Handle shared files if app was opened via sharing
        handleSharedFiles(intent)
        
        checkAndRequestPermissions()
        initializeMediaController()
        
        setContent {
            KraatzPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    // Initialize ViewModel
                    val viewModel: MusicPlayerViewModel = viewModel(
                        factory = MusicPlayerViewModelFactory(this@MainActivity)
                    )
                    
                    // Collect states
                    val songs by viewModel.songs.collectAsState()
                    val isLoadingMusic by viewModel.isLoadingMusic.collectAsState()
                    val playbackState by viewModel.playbackState.collectAsState()
                    val equalizerEnabled by viewModel.equalizerEnabled.collectAsState()
                    val currentPreset by viewModel.currentPreset.collectAsState()
                    val bandLevels by viewModel.bandLevels.collectAsState()
                    val bassBoostStrength by viewModel.bassBoostStrength.collectAsState()
                    val virtualizerStrength by viewModel.virtualizerStrength.collectAsState()
                    
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            SimpleMainScreen(
                                onStartListeningClick = {
                                    navController.navigate("music_library")
                                }
                            )
                        }
                        composable("music_library") {
                            MusicLibraryScreen(
                                songs = songs,
                                isLoading = isLoadingMusic,
                                onSongClick = { song ->
                                    viewModel.playSong(song)
                                    navController.navigate("player")
                                },
                                onPlayAllClick = {
                                    viewModel.playAllSongs()
                                    navController.navigate("player")
                                },
                                onEqualizerClick = {
                                    navController.navigate("equalizer")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onShareSong = { song ->
                                    // Convert Song to File for sharing
                                    // This is a simplified approach - in a real app you'd need proper file handling
                                    try {
                                        shareManager.shareNowPlaying(
                                            songTitle = song.title,
                                            artist = song.artist,
                                            album = song.album
                                        )
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                },
                                onSharePlaylist = {
                                    try {
                                        shareManager.sharePlaylist("My Playlist", emptyList()) // TODO: Convert songs to files
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                }
                            )
                        }
                        composable("player") {
                            PlayerScreen(
                                playbackState = playbackState,
                                onPlayPause = { viewModel.playPause() },
                                onNext = { viewModel.skipToNext() },
                                onPrevious = { viewModel.skipToPrevious() },
                                onSeek = { position -> viewModel.seekTo(position) },
                                onRepeatModeChange = { mode -> viewModel.setRepeatMode(mode) },
                                onShuffleModeChange = { enabled -> viewModel.setShuffleMode(enabled) },
                                onEqualizerClick = {
                                    navController.navigate("equalizer")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onShareNowPlaying = {
                                    try {
                                        val currentSong = playbackState.currentSong
                                        if (currentSong != null) {
                                            shareManager.shareNowPlaying(
                                                songTitle = currentSong.title,
                                                artist = currentSong.artist,
                                                album = currentSong.album
                                            )
                                        } else {
                                            shareManager.shareApp()
                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                }
                            )
                        }
                        composable("equalizer") {
                            EqualizerScreen(
                                isEnabled = equalizerEnabled,
                                currentPreset = currentPreset,
                                bandLevels = bandLevels,
                                bassBoostStrength = bassBoostStrength,
                                virtualizerStrength = virtualizerStrength,
                                onEnabledChange = { enabled -> viewModel.setEqualizerEnabled(enabled) },
                                onPresetChange = { preset -> viewModel.applyEqualizerPreset(preset) },
                                onBandLevelChange = { band, level -> viewModel.setBandLevel(band, level) },
                                onBassBoostChange = { strength -> viewModel.setBassBoost(strength) },
                                onVirtualizerChange = { strength -> viewModel.setVirtualizer(strength) },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    
    private fun checkAndRequestPermissions() {
        // Use the new PermissionManager for comprehensive permission handling
        permissionManager.requestStoragePermissions { granted ->
            if (granted) {
                onPermissionsGranted()
            } else {
                onPermissionsDenied()
            }
        }
    }
    
    /**
     * Handle files shared with the app
     */
    private fun handleSharedFiles(intent: Intent?) {
        if (intent == null) return
        
        try {
            val sharedFiles = shareManager.handleSharedFiles(intent)
            if (sharedFiles.isNotEmpty()) {
                // TODO: Add shared files to the music library or play them directly
                // For now, we'll just log that files were received
                println("Received ${sharedFiles.size} shared audio files")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * Handle new intents (when app is already running)
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleSharedFiles(intent)
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
