package com.kraatz.player.data.database.dao;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0019\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\'J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000bH\'J\u001e\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\'J\u001e\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\'J\u0018\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\'J\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000b2\u0006\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\u0006\u0010\u001a\u001a\u00020\bH\'J\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\u0006\u0010\u001c\u001a\u00020\bH\'J\u001c\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\u0006\u0010\u001e\u001a\u00020\bH\'J#\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0016\u0010#\u001a\u00020\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\'J\u001c\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\f0\u000b2\u0006\u0010&\u001a\u00020\bH\'J!\u0010\'\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006,"}, d2 = {"Lcom/kraatz/player/data/database/dao/SongDao;", "", "deleteSong", "", "song", "Lcom/kraatz/player/data/database/entity/Song;", "deleteSongByPath", "path", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAlbums", "Lkotlinx/coroutines/flow/Flow;", "", "getAllArtists", "getAllGenres", "getAllSongs", "getFavoriteSongs", "getMostPlayedSongs", "limit", "", "getRecentlyPlayedSongs", "getSongById", "id", "", "getSongByPath", "getSongsByAlbum", "album", "getSongsByArtist", "artist", "getSongsByGenre", "genre", "incrementPlayCount", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSong", "insertSongs", "songs", "searchSongs", "query", "updateFavoriteStatus", "isFavorite", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSong", "app_debug"})
public abstract interface SongDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs ORDER BY title ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getAllSongs();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs WHERE id = :id")
    public abstract kotlinx.coroutines.flow.Flow<com.kraatz.player.data.database.entity.Song> getSongById(long id);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs WHERE path = :path")
    public abstract kotlinx.coroutines.flow.Flow<com.kraatz.player.data.database.entity.Song> getSongByPath(@org.jetbrains.annotations.NotNull
    java.lang.String path);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs WHERE artist = :artist ORDER BY title ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getSongsByArtist(@org.jetbrains.annotations.NotNull
    java.lang.String artist);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs WHERE album = :album ORDER BY track_number ASC, title ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getSongsByAlbum(@org.jetbrains.annotations.NotNull
    java.lang.String album);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs WHERE genre = :genre ORDER BY title ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getSongsByGenre(@org.jetbrains.annotations.NotNull
    java.lang.String genre);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs WHERE is_favorite = 1 ORDER BY title ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getFavoriteSongs();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs WHERE title LIKE \'%\' || :query || \'%\' OR artist LIKE \'%\' || :query || \'%\' OR album LIKE \'%\' || :query || \'%\'")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> searchSongs(@org.jetbrains.annotations.NotNull
    java.lang.String query);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs ORDER BY last_played DESC LIMIT :limit")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getRecentlyPlayedSongs(int limit);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM songs ORDER BY play_count DESC LIMIT :limit")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kraatz.player.data.database.entity.Song>> getMostPlayedSongs(int limit);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract long insertSong(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Song song);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertSongs(@org.jetbrains.annotations.NotNull
    java.util.List<com.kraatz.player.data.database.entity.Song> songs);
    
    @androidx.room.Update
    public abstract void updateSong(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Song song);
    
    @androidx.room.Delete
    public abstract void deleteSong(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.entity.Song song);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM songs WHERE path = :path")
    public abstract java.lang.Object deleteSongByPath(@org.jetbrains.annotations.NotNull
    java.lang.String path, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE songs SET is_favorite = :isFavorite WHERE id = :id")
    public abstract java.lang.Object updateFavoriteStatus(long id, boolean isFavorite, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE songs SET play_count = play_count + 1, last_played = :timestamp WHERE id = :id")
    public abstract java.lang.Object incrementPlayCount(long id, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT DISTINCT artist FROM songs ORDER BY artist ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllArtists();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT DISTINCT album FROM songs ORDER BY album ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllAlbums();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT DISTINCT genre FROM songs WHERE genre IS NOT NULL ORDER BY genre ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllGenres();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 3)
    public final class DefaultImpls {
    }
}