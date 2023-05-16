package com.hfad.online_bookstore.data.sources.api

import com.hfad.online_bookstore.data.pojos.bookbyisbnpackage.BookByIsbnEntity
import com.hfad.online_bookstore.data.pojos.SearchResultForTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BookLibraryApi {

    //momentan am creat doar get-ul pentru cautati dupa 'title' urmeaza sa adaug si cautare dupa
    //autor si isbn probabil

    @GET("/search.json")
    suspend fun getResultsByTitle(@Query("title")title: String) : Response<SearchResultForTitle>

    @GET("/isbn/{bookIsbn}.json")
    suspend fun fetchDataByBookIsbn(@Path("bookIsbn")bookIsbn: String): Response<BookByIsbnEntity>


}