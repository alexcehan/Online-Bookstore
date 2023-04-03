package com.hfad.online_bookstore.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookLibraryApi {

    //momentan am creat doar get-ul pentru cautati dupa 'title' urmeaza sa adaug si cautare dupa
    //autor si isbn probabil

    @GET("/search.json")
    suspend fun getResultsByTitle(@Query("title")title: String) : Response<SearchResultForTitle>


}