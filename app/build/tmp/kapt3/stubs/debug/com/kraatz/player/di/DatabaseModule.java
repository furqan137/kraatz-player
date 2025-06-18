package com.kraatz.player.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\f"}, d2 = {"Lcom/kraatz/player/di/DatabaseModule;", "", "()V", "provideKraatzDatabase", "Lcom/kraatz/player/data/database/KraatzDatabase;", "context", "Landroid/content/Context;", "providePlaylistDao", "Lcom/kraatz/player/data/database/dao/PlaylistDao;", "database", "provideSongDao", "Lcom/kraatz/player/data/database/dao/SongDao;", "app_debug"})
@dagger.Module
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull
    public static final com.kraatz.player.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.kraatz.player.data.database.KraatzDatabase provideKraatzDatabase(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    public final com.kraatz.player.data.database.dao.SongDao provideSongDao(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.KraatzDatabase database) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    public final com.kraatz.player.data.database.dao.PlaylistDao providePlaylistDao(@org.jetbrains.annotations.NotNull
    com.kraatz.player.data.database.KraatzDatabase database) {
        return null;
    }
}