package com.hfad.online_bookstore.data.usecases

import com.hfad.online_bookstore.data.pojos.bookbyisbnpackage.BookByIsbnEntity
import kotlinx.coroutines.flow.Flow

interface FetchDataByBookIsbnUseCase {

    suspend operator fun invoke(bookIsbn: String): Flow<BookByIsbnEntity>
}