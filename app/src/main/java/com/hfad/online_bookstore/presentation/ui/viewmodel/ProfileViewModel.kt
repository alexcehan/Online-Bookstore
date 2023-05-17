package com.hfad.online_bookstore.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.hfad.online_bookstore.data.sources.db.BookDbDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val bookDbDao: BookDbDao
) : ViewModel(){

    private var _allBuyedBooks = bookDbDao.getAllThePurchasedBooks()
    val allBuyedBooks = _allBuyedBooks

    private var _countAllBuyedBooks = bookDbDao.getCountOfAllBuyedBooks()
    val countAllBuyedBooks = _countAllBuyedBooks

    private var _allWishlistItems = bookDbDao.getAllWishlistEntries()
    val allWishlistItems = _allWishlistItems

    private var _countAllWishlistBooks = bookDbDao.getCountOfAllWishlistBooks()
    val countAllWishlistBooks = _countAllWishlistBooks







}