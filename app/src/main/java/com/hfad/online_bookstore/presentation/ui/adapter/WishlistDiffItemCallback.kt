package com.hfad.online_bookstore.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hfad.online_bookstore.data.entities.Wishlist

class WishlistDiffItemCallback : DiffUtil.ItemCallback<Wishlist>() {

    override fun areItemsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean {
        return oldItem.wishlistId == newItem.wishlistId
    }

    override fun areContentsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean {
        return oldItem == newItem
    }
}