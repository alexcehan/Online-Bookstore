package com.hfad.online_bookstore.data.usecases

import com.hfad.online_bookstore.data.pojos.bookbyisbnpackage.BookByIsbnEntity
import com.hfad.online_bookstore.domain.repository.LibraryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDataByBookIsbnUseCaseImpl@Inject constructor(private val repository: LibraryRepository): FetchDataByBookIsbnUseCase {

    override suspend fun invoke(bookIsbn: String): Flow<BookByIsbnEntity> {
        return repository.fetchDataByBookIsbn(bookIsbn)
    }
}