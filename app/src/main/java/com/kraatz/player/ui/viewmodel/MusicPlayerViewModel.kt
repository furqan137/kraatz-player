package com.kraatz.player.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kraatz.player.data.MusicRepository
import com.kraatz.player.data.Song
import com.kraatz.player.equalizer.KraatzEqualizer
import com.kraatz.player.equalizer.KraatzPreset
import com.kraatz.player.player.MusicPlayerController
import com.kraatz.player.player.PlaybackState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MusicPlayerViewModel(
    private val context: Context
) : ViewModel() {
    
    private val musicRepository = MusicRepository(context)
    private val playerController = MusicPlayerController(context)
    
    // Music library state
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()
    
    private val _isLoadingMusic = MutableStateFlow(false)
    val isLoadingMusic: StateFlow<Boolean> = _isLoadingMusic.asStateFlow()
    
    // Player state
    val playbackState: StateFlow<PlaybackState> = playerController.playbackState
    val currentPlaylist: StateFlow<List<Song>> = playerController.currentPlaylist
    val currentSongIndex: StateFlow<Int> = playerController.currentSongIndex
    
    // Equalizer state
    private var equalizer: KraatzEqualizer? = null
    
    private val _equalizerEnabled = MutableStateFlow(false)
    val equalizerEnabled: StateFlow<Boolean> = _equalizerEnabled.asStateFlow()
    
    private val _currentPreset = MutableStateFlow(KraatzPreset.NORMAL)
    val currentPreset: StateFlow<KraatzPreset> = _currentPreset.asStateFlow()
    
    private val _bandLevels = MutableStateFlow<List<Short>>(emptyList())
    val bandLevels: StateFlow<List<Short>> = _bandLevels.asStateFlow()
    
    private val _bassBoostStrength = MutableStateFlow(0)
    val bassBoostStrength: StateFlow<Int> = _bassBoostStrength.asStateFlow()
    
    private val _virtualizerStrength = MutableStateFlow(0)
    val virtualizerStrength: StateFlow<Int> = _virtualizerStrength.asStateFlow()
    
    init {
        loadMusicLibrary()
        initializeEqualizer()
    }
    
    private fun loadMusicLibrary() {
        viewModelScope.launch {
            _isLoadingMusic.value = true
            try {
                val musicList = musicRepository.getAllSongs()
                _songs.value = musicList
            } catch (e: Exception) {
                e.printStackTrace()
                _songs.value = emptyList()
            } finally {
                _isLoadingMusic.value = false
            }
        }
    }
    
    private fun initializeEqualizer() {
        try {
            // Initialize with a dummy audio session ID for now
            // In a real implementation, this would come from the media player
            equalizer = KraatzEqualizer(0).apply {
                // Observe equalizer state changes
                viewModelScope.launch {
                    isEnabled.collect { enabled ->
                        _equalizerEnabled.value = enabled
                    }
                }
                
                viewModelScope.launch {
                    currentPreset.collect { preset ->
                        _currentPreset.value = preset
                    }
                }
                
                viewModelScope.launch {
                    bandLevels.collect { levels ->
                        _bandLevels.value = levels
                    }
                }
                
                viewModelScope.launch {
                    bassBoostStrength.collect { strength ->
                        _bassBoostStrength.value = strength
                    }
                }
                
                viewModelScope.launch {
                    virtualizerStrength.collect { strength ->
                        _virtualizerStrength.value = strength
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            equalizer = null
        }
    }
    
    // Music playback methods
    fun playSong(song: Song) {
        playerController.playSong(song)
    }
    
    fun playAllSongs(startIndex: Int = 0) {
        if (_songs.value.isNotEmpty()) {
            playerController.playPlaylist(_songs.value, startIndex)
        }
    }
    
    fun playPause() {
        if (playbackState.value.isPlaying) {
            playerController.pause()
        } else {
            playerController.play()
        }
    }
    
    fun skipToNext() {
        playerController.skipToNext()
    }
    
    fun skipToPrevious() {
        playerController.skipToPrevious()
    }
    
    fun seekTo(position: Long) {
        playerController.seekTo(position)
    }
    
    fun setRepeatMode(repeatMode: Int) {
        playerController.setRepeatMode(repeatMode)
    }
    
    fun setShuffleMode(enabled: Boolean) {
        playerController.setShuffleMode(enabled)
    }
    
    fun setPlaybackSpeed(speed: Float) {
        playerController.setPlaybackSpeed(speed)
    }
    
    // Equalizer methods
    fun setEqualizerEnabled(enabled: Boolean) {
        equalizer?.setEnabled(enabled)
    }
    
    fun applyEqualizerPreset(preset: KraatzPreset) {
        equalizer?.applyPreset(preset)
    }
    
    fun setBandLevel(band: Int, level: Short) {
        equalizer?.setBandLevel(band.toShort(), level)
    }
    
    fun setBassBoost(strength: Int) {
        equalizer?.setBassBoost(strength)
    }
    
    fun setVirtualizer(strength: Int) {
        equalizer?.setVirtualizer(strength)
    }
    
    // Search functionality
    fun searchSongs(query: String): List<Song> {
        return if (query.isBlank()) {
            _songs.value
        } else {
            _songs.value.filter { song ->
                song.title.contains(query, ignoreCase = true) ||
                song.artist.contains(query, ignoreCase = true) ||
                song.album.contains(query, ignoreCase = true)
            }
        }
    }
    
    // Refresh music library
    fun refreshMusicLibrary() {
        loadMusicLibrary()
    }
    
    override fun onCleared() {
        super.onCleared()
        playerController.release()
        equalizer?.release()
    }
}

// Factory for creating the ViewModel with Context
class MusicPlayerViewModelFactory(
    private val context: Context
) : androidx.lifecycle.ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicPlayerViewModel::class.java)) {
            return MusicPlayerViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
