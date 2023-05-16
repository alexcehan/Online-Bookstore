package com.hfad.online_bookstore.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "buyed_books",
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
    ])
data class BuyedBook(
    @PrimaryKey(autoGenerate = true)
    var purchaseId: Long = 0L,

    @ColumnInfo(name = "userId")
    var userId: Long = 0L,

    @ColumnInfo(name = "bookId")
    var bookId: String = ""
) {





}