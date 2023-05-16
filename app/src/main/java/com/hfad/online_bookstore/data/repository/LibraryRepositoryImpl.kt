package com.hfad.online_bookstore.data.repository

import com.hfad.online_bookstore.data.pojos.bookbyisbnpackage.BookByIsbnEntity
import com.hfad.online_bookstore.data.sources.api.BookLibraryApi
import com.hfad.online_bookstore.domain.repository.LibraryRepository
import com.hfad.online_bookstore.data.pojos.SearchResultForTitle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LibraryRepositoryImpl @Inject constructor(private val api: BookLibraryApi): LibraryRepository {


    override suspend fun fetchDataResultByTitle(bookTitle: String): Flow<SearchResultForTitle> {
        return flow {
            val response = api.getResultsByTitle(bookTitle)

            if (response.isSuccessful) {
                val data = response.body()
                emit(data!!)
            }
        }
    }

    override suspend fun fetchDataByBookIsbn(bookIsbn: String): Flow<BookByIsbnEntity> {
        return flow {
            val response = api.fetchDataByBookIsbn(bookIsbn)

            if(response.isSuccessful){
                val data = response.body()
                emit(data!!)
            }
        }

    }

    //    override suspend fun getResultsByTitle(bookTitle: String): Flow<Result<ContactsContract.Data>> {
//        val listOfBooks = MutableLiveData<List<BookEntity>>()
//
//        try {
//            val response = api.getResultsByTitle(bookTitle)
//            listOfBooks.value=response.body()!!.docs
//
//
//        } catch (e: Exception) {
//            Log.e("Exception IMPL", "Error: ${e.message}")
//        }
//
//        return listOfBooks
//    }
}