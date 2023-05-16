package com.hfad.online_bookstore.data.sources.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfad.online_bookstore.data.entities.*

@Database(entities = [User::class, Book::class, Cart::class, Wishlist::class, BuyedBook::class, Author::class], version = 1, exportSchema = false)
abstract class BooksLocalDatabase: RoomDatabase() {
    abstract val bookDbDao: BookDbDao

    companion object {
        @Volatile
        private var INSTANCE: BooksLocalDatabase? = null

        fun getInstance(context: Context): BooksLocalDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BooksLocalDatabase::class.java,
                        "books_database").build()
                    INSTANCE = instance


                }
                return instance
            }
        }
    }
}