package com.hfad.online_bookstore.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
class Author(
    @PrimaryKey(autoGenerate = true)
    var authorId: Long = 0L,

    @ColumnInfo(name = "olidKey")
    var olid: String = "",

    @ColumnInfo(name = "fullName")
    var authorName: String = ""
) {


}