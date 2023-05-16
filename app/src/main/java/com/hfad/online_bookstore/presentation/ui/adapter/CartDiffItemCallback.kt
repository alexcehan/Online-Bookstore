package com.hfad.online_bookstore.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.data.entities.Cart

class CartDiffItemCallback: DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.bookId == newItem.bookId
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}