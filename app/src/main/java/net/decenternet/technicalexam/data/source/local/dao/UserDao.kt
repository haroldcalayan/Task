package net.decenternet.technicalexam.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import net.decenternet.technicalexam.base.BaseDao
import net.decenternet.technicalexam.data.source.local.entity.User

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun all(): List<User>?

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun get(id: Int): User?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC LIMIT 1")
    suspend fun getFirst(): User?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun nukeTable()

    companion object {
        const val TABLE_NAME = "user"
    }

}