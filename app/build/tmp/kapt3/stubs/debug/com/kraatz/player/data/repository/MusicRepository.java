package com.kraatz.player.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0019\u001a\u00020\fH\u0002J\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001c0\u001bJ\u0012\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001c0\u001bJ\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001c0\u001bJ\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001c0\u001bJ\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001bJ\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001bJ\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001bJ\u0016\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001b2\u0006\u0010%\u001a\u00020\fJ\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001b2\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001bJ\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u001b2\u0006\u0010%\u001a\u00020\fJ\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001b2\u0006\u0010*\u001a\u00020\u0011J\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001b2\u0006\u0010,\u001a\u00020\u0011J\u0019\u0010-\u001a\u00020\n2\u0006\u0010%\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J!\u0010/\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020!0\u001cH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001c0\u001b2\u0006\u00103\u001a\u00020\u0011J!\u00104\u001a\u00020\n2\u0006\u0010%\u001a\u00020\f2\u0006\u00105\u001a\u000206H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00107J\u0019\u00108\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00069"}, d2 = {"Lcom/kraatz/player/data/repository/MusicRepository;", "", "songDao", "Lcom/kraatz/player/data/database/dao/SongDao;", "playlistDao", "Lcom/kraatz/player/data/database/dao/PlaylistDao;", "context", "Landroid/content/Context;", "(Lcom/kraatz/player/data/database/dao/SongDao;Lcom/kraatz/player/data/database/dao/PlaylistDao;Landroid/content/Context;)V", "addSongToPlaylist", "", "playlistId", "", "songId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createPlaylist", "name", "", "description", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlaylist", "playlist", "Lcom/kraatz/player/data/database/entity/Playlist;", "(Lcom/kraatz/player/data/database/entity/Playlist;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlbumArtUri", "albumId", "getAllAlbums", "Lkotlinx/coroutines/flow/Flow;", "", "getAllArtists", "getAllGenres", "getAllPlaylists", "getAllSongs", "Lcom/kraatz/player/data/database/entity/Song;", "getFavoriteSongs", "getMostPlayedSongs", "getPlaylistById", "id", "getPlaylistSongs", "getRecentlyPlayedSongs", "getSongById", "getSongsByAlbum", "album", "getSongsByArtist", "artist", "incrementPlayCount", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeSongFromPlaylist", "scanForMusic", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchSongs", "query", "updateFavoriteStatus", "isFavorite", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlaylist", "app_debug"})
@javax.inject.Singleton
public final class MusicRepository {
    private final com.kraatz.player.data.database.dao.SongDao songDao = null;
    private final com.kraatz.player.data.database.dao.PlaylistDao playlistDao = null;
    private final android.content.Context context = null;
    
    @javax.inject.Inject
    public MusicRepository(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.dao.SongDao songDao, @org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.dao.PlaylistDao playlistDao, @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getAllSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.kraatz.player.data.database.entity.Song> getSongById(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getSongsByArtist(@org.jetbrains.annotations.NotNull
    java.lang.String artist) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getSongsByAlbum(@org.jetbrains.annotations.NotNull
    java.lang.String album) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getFavoriteSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> searchSongs(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getRecentlyPlayedSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getMostPlayedSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllArtists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllAlbums() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllGenres() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateFavoriteStatus(long id, boolean isFavorite, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object incrementPlayCount(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Playlist>> getAllPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.kraatz.player.data.database.entity.Playlist> getPlaylistById(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getPlaylistSongs(long playlistId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object createPlaylist(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updatePlaylist(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Playlist playlist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deletePlaylist(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Playlist playlist, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addSongToPlaylist(long playlistId, long songId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object removeSongFromPlaylist(long playlistId, long songId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object scanForMusic(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.kraatz.player.data.database.entity.Song>> continuation) {
        return null;
    }
    
    private final java.lang.String getAlbumArtUri(long albumId) {
        return null;
    }
}