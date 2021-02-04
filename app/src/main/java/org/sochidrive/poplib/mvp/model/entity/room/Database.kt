package org.sochidrive.poplib.mvp.model.entity.room

import androidx.room.RoomDatabase
import org.sochidrive.poplib.mvp.model.entity.room.dao.RepositoryDao
import org.sochidrive.poplib.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }

}