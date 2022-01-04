package net.decenternet.technicalexam.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.decenternet.technicalexam.data.source.local.dao.UserDao
import net.decenternet.technicalexam.data.source.local.entity.User

const val VERSION_NUMBER = 1

@Database(
    entities = [User::class],
    version = VERSION_NUMBER
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
}