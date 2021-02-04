package org.sochidrive.poplib.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.sochidrive.poplib.App
import org.sochidrive.poplib.mvp.model.cache.IGithubRepositoriesCache
import org.sochidrive.poplib.mvp.model.cache.IGithubUsersCache
import org.sochidrive.poplib.mvp.model.cache.room.RoomGithubRepositoriesCache
import org.sochidrive.poplib.mvp.model.cache.room.RoomGithubUsersCache
import org.sochidrive.poplib.mvp.model.entity.room.Database
import org.sochidrive.poplib.mvp.model.entity.room.MIGRATION_1_2
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        //.fallbackToDestructiveMigration() - для разрушения бд и создания новой
        .addMigrations(MIGRATION_1_2)
        .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }
}