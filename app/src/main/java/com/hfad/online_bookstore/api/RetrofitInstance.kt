package com.hfad.online_bookstore.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//am vazut ca sunt mai multe abordari pentru creat clasa asta, pentru ceva organizare am preferat
// sa o fac intr-un file separat


    val api: BookLibraryApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://openlibrary.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookLibraryApi::class.java)
    }
}