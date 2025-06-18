package com.kraatz.player.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kraatz.player.data.database.entity.Playlist;
import com.kraatz.player.data.database.entity.PlaylistSong;
import com.kraatz.player.data.database.entity.Song;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlaylistDao_Impl implements PlaylistDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Playlist> __insertionAdapterOfPlaylist;

  private final EntityInsertionAdapter<PlaylistSong> __insertionAdapterOfPlaylistSong;

  private final EntityDeletionOrUpdateAdapter<Playlist> __deletionAdapterOfPlaylist;

  private final EntityDeletionOrUpdateAdapter<PlaylistSong> __deletionAdapterOfPlaylistSong;

  private final EntityDeletionOrUpdateAdapter<Playlist> __updateAdapterOfPlaylist;

  private final SharedSQLiteStatement __preparedStmtOfRemoveSongFromPlaylist;

  private final SharedSQLiteStatement __preparedStmtOfDecrementPositionsAfter;

  public PlaylistDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlaylist = new EntityInsertionAdapter<Playlist>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `playlists` (`id`,`name`,`description`,`created_at`,`updated_at`,`cover_art_uri`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Playlist value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getCreatedAt());
        stmt.bindLong(5, value.getUpdatedAt());
        if (value.getCoverArtUri() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCoverArtUri());
        }
      }
    };
    this.__insertionAdapterOfPlaylistSong = new EntityInsertionAdapter<PlaylistSong>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `playlist_songs` (`id`,`playlist_id`,`song_id`,`position`,`added_at`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistSong value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getPlaylistId());
        stmt.bindLong(3, value.getSongId());
        stmt.bindLong(4, value.getPosition());
        stmt.bindLong(5, value.getAddedAt());
      }
    };
    this.__deletionAdapterOfPlaylist = new EntityDeletionOrUpdateAdapter<Playlist>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `playlists` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Playlist value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__deletionAdapterOfPlaylistSong = new EntityDeletionOrUpdateAdapter<PlaylistSong>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `playlist_songs` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistSong value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPlaylist = new EntityDeletionOrUpdateAdapter<Playlist>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `playlists` SET `id` = ?,`name` = ?,`description` = ?,`created_at` = ?,`updated_at` = ?,`cover_art_uri` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Playlist value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getCreatedAt());
        stmt.bindLong(5, value.getUpdatedAt());
        if (value.getCoverArtUri() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCoverArtUri());
        }
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfRemoveSongFromPlaylist = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDecrementPositionsAfter = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE playlist_songs SET position = position - 1 WHERE playlist_id = ? AND position > ?";
        return _query;
      }
    };
  }

  @Override
  public long insertPlaylist(final Playlist playlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfPlaylist.insertAndReturnId(playlist);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object insertPlaylistSong(final PlaylistSong playlistSong,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlaylistSong.insert(playlistSong);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public void deletePlaylist(final Playlist playlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPlaylist.handle(playlist);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object deletePlaylistSong(final PlaylistSong playlistSong,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPlaylistSong.handle(playlistSong);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public void updatePlaylist(final Playlist playlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPlaylist.handle(playlist);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object removeSongFromPlaylist(final long playlistId, final long songId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveSongFromPlaylist.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playlistId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, songId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfRemoveSongFromPlaylist.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object decrementPositionsAfter(final long playlistId, final int position,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDecrementPositionsAfter.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, playlistId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, position);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDecrementPositionsAfter.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<Playlist>> getAllPlaylists() {
    final String _sql = "SELECT * FROM playlists ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"playlists"}, new Callable<List<Playlist>>() {
      @Override
      public List<Playlist> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfCoverArtUri = CursorUtil.getColumnIndexOrThrow(_cursor, "cover_art_uri");
          final List<Playlist> _result = new ArrayList<Playlist>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Playlist _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpCoverArtUri;
            if (_cursor.isNull(_cursorIndexOfCoverArtUri)) {
              _tmpCoverArtUri = null;
            } else {
              _tmpCoverArtUri = _cursor.getString(_cursorIndexOfCoverArtUri);
            }
            _item = new Playlist(_tmpId,_tmpName,_tmpDescription,_tmpCreatedAt,_tmpUpdatedAt,_tmpCoverArtUri);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Playlist> getPlaylistById(final long id) {
    final String _sql = "SELECT * FROM playlists WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"playlists"}, new Callable<Playlist>() {
      @Override
      public Playlist call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfCoverArtUri = CursorUtil.getColumnIndexOrThrow(_cursor, "cover_art_uri");
          final Playlist _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpCoverArtUri;
            if (_cursor.isNull(_cursorIndexOfCoverArtUri)) {
              _tmpCoverArtUri = null;
            } else {
              _tmpCoverArtUri = _cursor.getString(_cursorIndexOfCoverArtUri);
            }
            _result = new Playlist(_tmpId,_tmpName,_tmpDescription,_tmpCreatedAt,_tmpUpdatedAt,_tmpCoverArtUri);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Song>> getPlaylistSongs(final long playlistId) {
    final String _sql = "\n"
            + "        SELECT s.* FROM songs s \n"
            + "        INNER JOIN playlist_songs ps ON s.id = ps.song_id \n"
            + "        WHERE ps.playlist_id = ? \n"
            + "        ORDER BY ps.position ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playlistId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"songs","playlist_songs"}, new Callable<List<Song>>() {
      @Override
      public List<Song> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "album");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfAlbumArtUri = CursorUtil.getColumnIndexOrThrow(_cursor, "album_art_uri");
          final int _cursorIndexOfTrackNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "track_number");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "date_added");
          final int _cursorIndexOfDateModified = CursorUtil.getColumnIndexOrThrow(_cursor, "date_modified");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "is_favorite");
          final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "play_count");
          final int _cursorIndexOfLastPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "last_played");
          final List<Song> _result = new ArrayList<Song>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Song _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpArtist;
            if (_cursor.isNull(_cursorIndexOfArtist)) {
              _tmpArtist = null;
            } else {
              _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            }
            final String _tmpAlbum;
            if (_cursor.isNull(_cursorIndexOfAlbum)) {
              _tmpAlbum = null;
            } else {
              _tmpAlbum = _cursor.getString(_cursorIndexOfAlbum);
            }
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpAlbumArtUri;
            if (_cursor.isNull(_cursorIndexOfAlbumArtUri)) {
              _tmpAlbumArtUri = null;
            } else {
              _tmpAlbumArtUri = _cursor.getString(_cursorIndexOfAlbumArtUri);
            }
            final Integer _tmpTrackNumber;
            if (_cursor.isNull(_cursorIndexOfTrackNumber)) {
              _tmpTrackNumber = null;
            } else {
              _tmpTrackNumber = _cursor.getInt(_cursorIndexOfTrackNumber);
            }
            final Integer _tmpYear;
            if (_cursor.isNull(_cursorIndexOfYear)) {
              _tmpYear = null;
            } else {
              _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            }
            final String _tmpGenre;
            if (_cursor.isNull(_cursorIndexOfGenre)) {
              _tmpGenre = null;
            } else {
              _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            }
            final long _tmpSize;
            _tmpSize = _cursor.getLong(_cursorIndexOfSize);
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final long _tmpDateModified;
            _tmpDateModified = _cursor.getLong(_cursorIndexOfDateModified);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final int _tmpPlayCount;
            _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
            final Long _tmpLastPlayed;
            if (_cursor.isNull(_cursorIndexOfLastPlayed)) {
              _tmpLastPlayed = null;
            } else {
              _tmpLastPlayed = _cursor.getLong(_cursorIndexOfLastPlayed);
            }
            _item = new Song(_tmpId,_tmpTitle,_tmpArtist,_tmpAlbum,_tmpDuration,_tmpPath,_tmpAlbumArtUri,_tmpTrackNumber,_tmpYear,_tmpGenre,_tmpSize,_tmpDateAdded,_tmpDateModified,_tmpIsFavorite,_tmpPlayCount,_tmpLastPlayed);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<PlaylistSong> getPlaylistSongEntries(final long playlistId) {
    final String _sql = "SELECT * FROM playlist_songs WHERE playlist_id = ? ORDER BY position ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playlistId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPlaylistId = CursorUtil.getColumnIndexOrThrow(_cursor, "playlist_id");
      final int _cursorIndexOfSongId = CursorUtil.getColumnIndexOrThrow(_cursor, "song_id");
      final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
      final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "added_at");
      final List<PlaylistSong> _result = new ArrayList<PlaylistSong>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PlaylistSong _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final long _tmpPlaylistId;
        _tmpPlaylistId = _cursor.getLong(_cursorIndexOfPlaylistId);
        final long _tmpSongId;
        _tmpSongId = _cursor.getLong(_cursorIndexOfSongId);
        final int _tmpPosition;
        _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
        final long _tmpAddedAt;
        _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
        _item = new PlaylistSong(_tmpId,_tmpPlaylistId,_tmpSongId,_tmpPosition,_tmpAddedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Object getMaxPosition(final long playlistId,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT MAX(position) FROM playlist_songs WHERE playlist_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playlistId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object isSongInPlaylist(final long playlistId, final long songId,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playlistId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, songId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
