package com.hfad.online_bookstore.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.online_bookstore.data.pojos.BookEntity
import com.hfad.online_bookstore.data.usecases.FetchDataByTitleUseCase
import com.hfad.online_bookstore.domain.repository.LibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val fetchDataByTitleUseCase: FetchDataByTitleUseCase
): ViewModel() {

    private var _booksByTitle = MutableLiveData<List<BookEntity>>()
    val booksByTitle = _booksByTitle
    private val _navigateToBookDetails = MutableLiveData<BookEntity?>()
    val navigateToBookDetails: LiveData<BookEntity?>
        get() = _navigateToBookDetails

    fun onBookClicked(bookIsbn: BookEntity) {
        _navigateToBookDetails.value = bookIsbn
    }

    fun onBookNavigated() {
        _navigateToBookDetails.value = null
    }

    fun getBooksByTitle(bookTitle: String) {
        _booksByTitle.value = emptyList()
        viewModelScope.launch {
            fetchDataByTitleUseCase(bookTitle).collect {
                result ->
                    if(result.docs.size > 0) {
                        _booksByTitle.value = result.docs
                        _booksByTitle.value!!.filter { bookEntity -> bookEntity.isbn != null }
                        _booksByTitle.value!!.forEach { bookEntity ->
                                    if (bookEntity.price == null) {
                                        bookEntity.price = generatePrice()
                                    }

                                    if(bookEntity.ratings_average == null) {
                                        bookEntity.ratings_average = generateRating()
                                    }


                        }
                    }
            }
        }
    }

    private fun generatePrice(): Double {
        val min = 10.0
        val max = 25.0
        return min + (max - min) * Math.random()
    }

    private fun generateRating(): Double {
        val min = 1.0
        val max = 5.0
        return min + (max - min) * Math.random()
    }

    private fun generateYear(): Int {
        val min = 1900
        val max = 2010
        return (min..max).random()
    }



}