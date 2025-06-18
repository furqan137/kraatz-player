package com.kraatz.player.data.database.entity;

import java.lang.System;

@androidx.room.Entity(tableName = "playlist_songs", foreignKeys = {@androidx.room.ForeignKey(entity = com.kraatz.player.data.database.entity.Playlist.class, childColumns = {"playlist_id"}, onDelete = 5, parentColumns = {"id"}), @androidx.room.ForeignKey(entity = com.kraatz.player.data.database.entity.Song.class, childColumns = {"song_id"}, onDelete = 5, parentColumns = {"id"})}, indices = {@androidx.room.Index(value = {"playlist_id"}), @androidx.room.Index(value = {"song_id"}), @androidx.room.Index(unique = true, value = {"playlist_id", "song_id"})})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lcom/kraatz/player/data/database/entity/PlaylistSong;", "", "id", "", "playlistId", "songId", "position", "", "addedAt", "(JJJIJ)V", "getAddedAt", "()J", "getId", "getPlaylistId", "getPosition", "()I", "getSongId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class PlaylistSong {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @androidx.room.ColumnInfo(name = "playlist_id")
    private final long playlistId = 0L;
    @androidx.room.ColumnInfo(name = "song_id")
    private final long songId = 0L;
    private final int position = 0;
    @androidx.room.ColumnInfo(name = "added_at")
    private final long addedAt = 0L;
    
    @org.jetbrains.annotations.NotNull
    public final com.kraatz.player.data.database.entity.PlaylistSong copy(long id, long playlistId, long songId, int position, long addedAt) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public PlaylistSong(long id, long playlistId, long songId, int position, long addedAt) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long getPlaylistId() {
        return 0L;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getSongId() {
        return 0L;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getPosition() {
        return 0;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getAddedAt() {
        return 0L;
    }
}