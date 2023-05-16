package com.hfad.online_bookstore.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hfad.online_bookstore.data.pojos.BookEntity

class BookEntityDiffItemCallback:DiffUtil.ItemCallback<BookEntity>() {

    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
        return oldItem == newItem
    }
}