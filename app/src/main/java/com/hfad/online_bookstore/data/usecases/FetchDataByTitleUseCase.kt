package com.hfad.online_bookstore.data.usecases

import com.hfad.online_bookstore.data.pojos.SearchResultForTitle
import kotlinx.coroutines.flow.Flow


interface FetchDataByTitleUseCase {

    suspend operator fun invoke(bookTitle: String): Flow<SearchResultForTitle>


}