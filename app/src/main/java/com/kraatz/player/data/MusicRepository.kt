package com.kraatz.player.data

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val data: String,
    val albumId: Long,
    val artistId: Long
) {
    fun toMediaItem(): MediaItem {
        return MediaItem.Builder()
            .setUri(data)
            .setMediaId(id.toString())
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(title)
                    .setArtist(artist)
                    .setAlbumTitle(album)
                    .build()
            )
            .build()
    }
    
    fun getDurationString(): String {
        val minutes = duration / 1000 / 60
        val seconds = (duration / 1000) % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}

class MusicRepository(private val context: Context) {
    
    private val contentResolver: ContentResolver = context.contentResolver
    
    suspend fun getAllSongs(): List<Song> = withContext(Dispatchers.IO) {
        val songs = mutableListOf<Song>()
        
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID
        )
        
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} = 1"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
        
        val cursor: Cursor? = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )
        
        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            val albumIdColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val artistIdColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID)
            
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val title = it.getString(titleColumn) ?: "Unknown Title"
                val artist = it.getString(artistColumn) ?: "Unknown Artist"
                val album = it.getString(albumColumn) ?: "Unknown Album"
                val duration = it.getLong(durationColumn)
                val data = it.getString(dataColumn) ?: ""
                val albumId = it.getLong(albumIdColumn)
                val artistId = it.getLong(artistIdColumn)
                
                // Only add songs with valid data
                if (data.isNotEmpty() && duration > 0) {
                    songs.add(
                        Song(
                            id = id,
                            title = title,
                            artist = artist,
                            album = album,
                            duration = duration,
                            data = data,
                            albumId = albumId,
                            artistId = artistId
                        )
                    )
                }
            }
        }
        
        songs
    }
    
    suspend fun getSongsByArtist(artistId: Long): List<Song> = withContext(Dispatchers.IO) {
        getAllSongs().filter { it.artistId == artistId }
    }
    
    suspend fun getSongsByAlbum(albumId: Long): List<Song> = withContext(Dispatchers.IO) {
        getAllSongs().filter { it.albumId == albumId }
    }
    
    suspend fun searchSongs(query: String): List<Song> = withContext(Dispatchers.IO) {
        getAllSongs().filter { song ->
            song.title.contains(query, ignoreCase = true) ||
            song.artist.contains(query, ignoreCase = true) ||
            song.album.contains(query, ignoreCase = true)
        }
    }
    
    fun getAlbumArtUri(albumId: Long): Uri {
        return Uri.parse("content://media/external/audio/albumart/$albumId")
    }
}
