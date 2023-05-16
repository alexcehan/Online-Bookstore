package com.hfad.online_bookstore.domain.repository

import com.hfad.online_bookstore.data.pojos.bookbyisbnpackage.BookByIsbnEntity
import com.hfad.online_bookstore.data.pojos.SearchResultForTitle
import kotlinx.coroutines.flow.Flow


interface LibraryRepository {


    suspend fun fetchDataResultByTitle(bookTitle: String): Flow<SearchResultForTitle>

    //suspend fun getResultsByTitle(title: String): Response<SearchResultForTitle>

    suspend fun fetchDataByBookIsbn(bookIsbn: String):Flow<BookByIsbnEntity>

}
