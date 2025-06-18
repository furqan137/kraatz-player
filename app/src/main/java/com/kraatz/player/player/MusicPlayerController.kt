package com.kraatz.player.player

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.kraatz.player.data.Song
import com.kraatz.player.service.SimpleMusicService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PlaybackState(
    val isPlaying: Boolean = false,
    val currentSong: Song? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val playbackSpeed: Float = 1.0f,
    val repeatMode: Int = Player.REPEAT_MODE_OFF,
    val shuffleMode: Boolean = false
)

class MusicPlayerController(private val context: Context) {
    
    private var mediaController: MediaController? = null
    private var controllerFuture: ListenableFuture<MediaController>? = null
    
    private val _playbackState = MutableStateFlow(PlaybackState())
    val playbackState: StateFlow<PlaybackState> = _playbackState.asStateFlow()
    
    private val _currentPlaylist = MutableStateFlow<List<Song>>(emptyList())
    val currentPlaylist: StateFlow<List<Song>> = _currentPlaylist.asStateFlow()
    
    private val _currentSongIndex = MutableStateFlow(0)
    val currentSongIndex: StateFlow<Int> = _currentSongIndex.asStateFlow()
    
    init {
        initializeController()
    }
    
    private fun initializeController() {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, SimpleMusicService::class.java)
        )
        
        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture?.addListener({
            try {
                mediaController = controllerFuture?.get()
                setupPlayerListener()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, MoreExecutors.directExecutor())
    }
    
    private fun setupPlayerListener() {
        mediaController?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlaybackState(isPlaying = isPlaying)
            }
            
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                val currentSong = getCurrentSongFromMediaItem(mediaItem)
                updatePlaybackState(currentSong = currentSong)
            }
            
            override fun onPlaybackStateChanged(playbackState: Int) {
                // Handle playback state changes
                when (playbackState) {
                    Player.STATE_READY -> {
                        updatePlaybackState(duration = mediaController?.duration ?: 0L)
                    }
                    Player.STATE_ENDED -> {
                        // Handle end of playback
                    }
                }
            }
            
            override fun onRepeatModeChanged(repeatMode: Int) {
                updatePlaybackState(repeatMode = repeatMode)
            }
            
            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                updatePlaybackState(shuffleMode = shuffleModeEnabled)
            }
        })
    }
    
    private fun getCurrentSongFromMediaItem(mediaItem: MediaItem?): Song? {
        if (mediaItem == null) return null
        
        val mediaId = mediaItem.mediaId
        return _currentPlaylist.value.find { it.id.toString() == mediaId }
    }
    
    private fun updatePlaybackState(
        isPlaying: Boolean? = null,
        currentSong: Song? = null,
        currentPosition: Long? = null,
        duration: Long? = null,
        playbackSpeed: Float? = null,
        repeatMode: Int? = null,
        shuffleMode: Boolean? = null
    ) {
        _playbackState.value = _playbackState.value.copy(
            isPlaying = isPlaying ?: _playbackState.value.isPlaying,
            currentSong = currentSong ?: _playbackState.value.currentSong,
            currentPosition = currentPosition ?: mediaController?.currentPosition ?: _playbackState.value.currentPosition,
            duration = duration ?: _playbackState.value.duration,
            playbackSpeed = playbackSpeed ?: _playbackState.value.playbackSpeed,
            repeatMode = repeatMode ?: _playbackState.value.repeatMode,
            shuffleMode = shuffleMode ?: _playbackState.value.shuffleMode
        )
    }
    
    // Playback control methods
    fun playSong(song: Song) {
        val mediaItem = song.toMediaItem()
        mediaController?.setMediaItem(mediaItem)
        mediaController?.prepare()
        mediaController?.play()
        updatePlaybackState(currentSong = song)
    }
    
    fun playPlaylist(songs: List<Song>, startIndex: Int = 0) {
        if (songs.isEmpty()) return
        
        _currentPlaylist.value = songs
        _currentSongIndex.value = startIndex
        
        val mediaItems = songs.map { it.toMediaItem() }
        mediaController?.setMediaItems(mediaItems, startIndex, 0)
        mediaController?.prepare()
        mediaController?.play()
        
        updatePlaybackState(currentSong = songs[startIndex])
    }
    
    fun play() {
        mediaController?.play()
    }
    
    fun pause() {
        mediaController?.pause()
    }
    
    fun stop() {
        mediaController?.stop()
    }
    
    fun skipToNext() {
        mediaController?.seekToNext()
        val nextIndex = (_currentSongIndex.value + 1) % _currentPlaylist.value.size
        _currentSongIndex.value = nextIndex
        if (_currentPlaylist.value.isNotEmpty()) {
            updatePlaybackState(currentSong = _currentPlaylist.value[nextIndex])
        }
    }
    
    fun skipToPrevious() {
        mediaController?.seekToPrevious()
        val prevIndex = if (_currentSongIndex.value > 0) {
            _currentSongIndex.value - 1
        } else {
            _currentPlaylist.value.size - 1
        }
        _currentSongIndex.value = prevIndex
        if (_currentPlaylist.value.isNotEmpty()) {
            updatePlaybackState(currentSong = _currentPlaylist.value[prevIndex])
        }
    }
    
    fun seekTo(position: Long) {
        mediaController?.seekTo(position)
    }
    
    fun setRepeatMode(repeatMode: Int) {
        mediaController?.repeatMode = repeatMode
    }
    
    fun setShuffleMode(enabled: Boolean) {
        mediaController?.shuffleModeEnabled = enabled
    }
    
    fun setPlaybackSpeed(speed: Float) {
        mediaController?.setPlaybackSpeed(speed)
    }
    
    // Getter methods
    fun isPlaying(): Boolean = mediaController?.isPlaying ?: false
    fun getCurrentPosition(): Long = mediaController?.currentPosition ?: 0L
    fun getDuration(): Long = mediaController?.duration ?: 0L
    
    fun release() {
        mediaController?.release()
        controllerFuture?.let { future ->
            MediaController.releaseFuture(future)
        }
    }
}

