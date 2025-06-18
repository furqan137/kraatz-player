package com.kraatz.player.data.database.dao;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\'J\u0019\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00120\u0011H\'J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00112\u0006\u0010\u0016\u001a\u00020\u0005H\'J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00122\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00120\u00112\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\'J\u0019\u0010\u001b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ!\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/kraatz/player/data/database/dao/PlaylistDao;", "", "decrementPositionsAfter", "", "playlistId", "", "position", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlaylist", "playlist", "Lcom/kraatz/player/data/database/entity/Playlist;", "deletePlaylistSong", "playlistSong", "Lcom/kraatz/player/data/database/entity/PlaylistSong;", "(Lcom/kraatz/player/data/database/entity/PlaylistSong;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPlaylists", "Lkotlinx/coroutines/flow/Flow;", "", "getMaxPosition", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPlaylistById", "id", "getPlaylistSongEntries", "getPlaylistSongs", "Lcom/kraatz/player/data/database/entity/Song;", "insertPlaylist", "insertPlaylistSong", "isSongInPlaylist", "songId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeSongFromPlaylist", "updatePlaylist", "app_debug"})
public abstract interface PlaylistDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM playlists ORDER BY name ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Playlist>> getAllPlaylists();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM playlists WHERE id = :id")
    public abstract kotlinx.coroutines.flow.Flow<com.kraatz.player.data.database.entity.Playlist> getPlaylistById(long id);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "\n        SELECT s.* FROM songs s \n        INNER JOIN playlist_songs ps ON s.id = ps.song_id \n        WHERE ps.playlist_id = :playlistId \n        ORDER BY ps.position ASC\n    ")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getPlaylistSongs(long playlistId);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM playlist_songs WHERE playlist_id = :playlistId ORDER BY position ASC")
    public abstract java.util.List<com.kraatz.player.data.database.entity.PlaylistSong> getPlaylistSongEntries(long playlistId);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract long insertPlaylist(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Playlist playlist);
    
    @androidx.room.Update
    public abstract void updatePlaylist(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Playlist playlist);
    
    @androidx.room.Delete
    public abstract void deletePlaylist(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Playlist playlist);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertPlaylistSong(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.PlaylistSong playlistSong, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deletePlaylistSong(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.PlaylistSong playlistSong, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM playlist_songs WHERE playlist_id = :playlistId AND song_id = :songId")
    public abstract java.lang.Object removeSongFromPlaylist(long playlistId, long songId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT MAX(position) FROM playlist_songs WHERE playlist_id = :playlistId")
    public abstract java.lang.Object getMaxPosition(long playlistId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT(*) FROM playlist_songs WHERE playlist_id = :playlistId AND song_id = :songId")
    public abstract java.lang.Object isSongInPlaylist(long playlistId, long songId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE playlist_songs SET position = position - 1 WHERE playlist_id = :playlistId AND position > :position")
    public abstract java.lang.Object decrementPositionsAfter(long playlistId, int position, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}