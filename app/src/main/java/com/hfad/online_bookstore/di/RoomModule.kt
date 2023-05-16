package com.hfad.online_bookstore.di

import android.content.Context
import androidx.room.Room
import com.hfad.online_bookstore.data.sources.db.BookDbDao
import com.hfad.online_bookstore.data.sources.db.BooksLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): BooksLocalDatabase {
        return Room.databaseBuilder(context, BooksLocalDatabase::class.java, "books_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideBookDao(booksLocalDatabase: BooksLocalDatabase): BookDbDao {
        return booksLocalDatabase.bookDbDao
    }


}