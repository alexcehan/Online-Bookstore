package com.hfad.online_bookstore.data.entities

import androidx.room.*

@Entity(tableName = "wishlist",
        foreignKeys = [
            ForeignKey(
                entity = User::class,
                parentColumns = ["userId"],
                childColumns = ["userId"],
                onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                entity = Book::class,
                parentColumns = ["bookId"],
                childColumns = ["bookId"],
                onDelete = ForeignKey.CASCADE
            )
        ], indices = [Index(value = ["bookId"], unique = true)])
data class Wishlist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wishlistId")
    var wishlistId: Long = 0L,

    @ColumnInfo(name = "userId")
    var userId: Long,

    @ColumnInfo(name = "bookId")
    var bookId: String,

    @ColumnInfo(name = "image")
    var imageName: String


) {
}