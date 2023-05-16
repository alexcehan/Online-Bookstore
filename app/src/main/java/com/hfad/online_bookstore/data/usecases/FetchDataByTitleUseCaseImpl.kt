package com.hfad.online_bookstore.data.usecases

import com.hfad.online_bookstore.data.pojos.SearchResultForTitle
import com.hfad.online_bookstore.domain.repository.LibraryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDataByTitleUseCaseImpl @Inject constructor(private val repository: LibraryRepository): FetchDataByTitleUseCase {

    override suspend fun invoke(bookTitle: String): Flow<SearchResultForTitle> {
        return repository.fetchDataResultByTitle(bookTitle)
    }
}