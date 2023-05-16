package com.hfad.online_bookstore.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.online_bookstore.data.entities.BuyedBook
import com.hfad.online_bookstore.data.entities.Cart
import com.hfad.online_bookstore.data.sources.db.BookDbDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShopAndWishListViewModel @Inject constructor(
    private val bookDbDao: BookDbDao
): ViewModel() {

    var mutableCart: Cart? = null


    private var _booksInTheCart = bookDbDao.getAllCartEntries()
    val booksInTheCart = _booksInTheCart

    private var _booksInTheWishlist = bookDbDao.getAllWishlistEntries()
    val booksInTheWishlist = _booksInTheWishlist

    private var _totalToPayForCart = bookDbDao.getTotalToPayCart()
    val totalToPayForCart = _totalToPayForCart

    fun addCartToPurchasedList() {
        val currentList = mutableListOf<BuyedBook>()
        booksInTheCart.value?.forEach { book ->
            currentList.add(BuyedBook(0,1, book.bookId, ))
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                bookDbDao.insertAll(currentList)
                bookDbDao.deleteAllEntriesInShoppingCart()
            }
        }
    }

    fun onCartButtonClicked(bookId: String) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                bookDbDao.insert(Cart(0, 1, bookId, "image"))
                bookDbDao.deleteWishListEntry(bookId)
            }

        }
    }

    fun onDeleteButtonClick(bookId: String, isCartItem: Boolean) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (isCartItem) {
                    bookDbDao.deleteCartEntry(bookId)
                } else {
                    bookDbDao.deleteWishListEntry(bookId)
                }

            }

        }
    }







}