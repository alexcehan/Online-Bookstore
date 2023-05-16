package com.hfad.online_bookstore.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hfad.online_bookstore.data.pojos.BookEntity
import org.jetbrains.annotations.NotNull

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = false)
    var bookId: String = "",

    @ColumnInfo(name = "title")
    var bookTitle: String,

    @ColumnInfo(name = "author")
    var bookAuthor: String,

    @ColumnInfo(name = "published_year")
    var publishYear: Int,

    @ColumnInfo(name = "rating")
    var bookRating: Double,

    @ColumnInfo(name = "price")
    var bookPrice: Double
) {
}