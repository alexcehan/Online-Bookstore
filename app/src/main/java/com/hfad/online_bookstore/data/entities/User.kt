package com.hfad.online_bookstore.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "first_name")
    var firstName: String = "",

    @ColumnInfo(name = "last_name")
    var lastName: String ="",

    @ColumnInfo(name = "username")
    var username: String = "",

    @ColumnInfo(name = "password")
    var password: String = ""
) {
}