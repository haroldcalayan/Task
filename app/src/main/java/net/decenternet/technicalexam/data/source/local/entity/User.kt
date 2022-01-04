package net.decenternet.technicalexam.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var username: String,
    var password: String
)