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




}