package com.kraatz.player.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0007H\u0002J\u0006\u0010\"\u001a\u00020\u0016J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\b\u0010&\u001a\u00020$H\u0002J\b\u0010\'\u001a\u00020$H\u0016J\b\u0010(\u001a\u00020$H\u0016J\u0012\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010*\u001a\u00020+H\u0016J\u0006\u0010,\u001a\u00020$J\u000e\u0010-\u001a\u00020$2\u0006\u0010!\u001a\u00020\u0007J\u001e\u0010.\u001a\u00020$2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0007002\b\b\u0002\u00101\u001a\u000202J\u000e\u00103\u001a\u00020$2\u0006\u00104\u001a\u00020\u0005J\u000e\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\fJ\u000e\u00107\u001a\u00020$2\u0006\u00108\u001a\u00020\tJ\u0006\u00109\u001a\u00020$J\u0006\u0010:\u001a\u00020$J\u0010\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020 H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lcom/kraatz/player/service/MusicService;", "Landroidx/media3/session/MediaSessionService;", "()V", "_currentPosition", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_currentSong", "Lcom/kraatz/player/data/database/entity/Song;", "_isPlaying", "", "_isShuffleEnabled", "_playbackMode", "Lcom/kraatz/player/service/PlaybackMode;", "currentPosition", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentPosition", "()Lkotlinx/coroutines/flow/StateFlow;", "currentSong", "getCurrentSong", "isPlaying", "isShuffleEnabled", "kraatzEqualizer", "Lcom/kraatz/player/equalizer/KraatzEqualizer;", "mediaSession", "Landroidx/media3/session/MediaSession;", "playbackMode", "getPlaybackMode", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "createMediaItemFromSong", "Landroidx/media3/common/MediaItem;", "song", "getKraatzEqualizer", "handlePlaybackEnded", "", "initializeMediaSession", "initializePlayer", "onCreate", "onDestroy", "onGetSession", "controllerInfo", "Landroidx/media3/session/MediaSession$ControllerInfo;", "playPause", "playSong", "playSongs", "songs", "", "startIndex", "", "seekTo", "position", "setRepeatMode", "mode", "setShuffleEnabled", "enabled", "skipToNext", "skipToPrevious", "updateCurrentSongFromMediaItem", "mediaItem", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint
public final class MusicService extends androidx.media3.session.MediaSessionService {
    private androidx.media3.session.MediaSession mediaSession;
    private androidx.media3.exoplayer.ExoPlayer player;
    private com.kraatz.player.equalizer.KraatzEqualizer kraatzEqualizer;
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.kraatz.player.data.database.entity.Song> _currentSong = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.kraatz.player.data.database.entity.Song> currentSong = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isPlaying = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPlaying = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _currentPosition = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> currentPosition = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.kraatz.player.service.PlaybackMode> _playbackMode = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.kraatz.player.service.PlaybackMode> playbackMode = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isShuffleEnabled = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isShuffleEnabled = null;
    
    public MusicService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.kraatz.player.data.database.entity.Song> getCurrentSong() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPlaying() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getCurrentPosition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.kraatz.player.service.PlaybackMode> getPlaybackMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isShuffleEnabled() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    private final void initializePlayer() {
    }
    
    private final void initializeMediaSession() {
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public androidx.media3.session.MediaSession onGetSession(@org.jetbrains.annotations.NotNull
    androidx.media3.session.MediaSession.ControllerInfo controllerInfo) {
        return null;
    }
    
    public final void playSong(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Song song) {
    }
    
    public final void playSongs(@org.jetbrains.annotations.NotNull
    java.util.List<com.kraatz.player.data.database.entity.Song> songs, int startIndex) {
    }
    
    public final void playPause() {
    }
    
    public final void skipToNext() {
    }
    
    public final void skipToPrevious() {
    }
    
    public final void seekTo(long position) {
    }
    
    public final void setShuffleEnabled(boolean enabled) {
    }
    
    public final void setRepeatMode(@org.jetbrains.annotations.NotNull
    com.kraatz.player.service.PlaybackMode mode) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.kraatz.player.equalizer.KraatzEqualizer getKraatzEqualizer() {
        return null;
    }
    
    private final androidx.media3.common.MediaItem createMediaItemFromSong(com.kraatz.player.data.database.entity.Song song) {
        return null;
    }
    
    private final void updateCurrentSongFromMediaItem(androidx.media3.common.MediaItem mediaItem) {
    }
    
    private final void handlePlaybackEnded() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
}