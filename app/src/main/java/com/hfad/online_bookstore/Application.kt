package com.hfad.online_bookstore

import com.hfad.online_bookstore.data.entities.User
import com.hfad.online_bookstore.data.sources.db.BooksLocalDatabase
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: android.app.Application() {

    val myDatabase: BooksLocalDatabase by lazy { BooksLocalDatabase.getInstance(applicationContext) }
    override fun onCreate() {
        super.onCreate()

    }




}