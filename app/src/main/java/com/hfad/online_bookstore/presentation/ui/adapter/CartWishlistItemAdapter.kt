package com.hfad.online_bookstore.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.databinding.BookItemBinding
import com.squareup.picasso.Picasso

class CartWishlistItemAdapter(val isCartItem: Boolean, val clickOnCartButton:(bookId: String)->Unit, val clickOnWishButton:(bookId:String)->Unit): ListAdapter<Book, CartWishlistItemAdapter.CartViewHolder>(CartDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, isCartItem, clickOnCartButton, clickOnWishButton)
    }


    class CartViewHolder(val binding: BookItemBinding): RecyclerView.ViewHolder(binding.root) {


        companion object {
            fun inflateFrom(parent: ViewGroup): CartViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookItemBinding.inflate(layoutInflater, parent, false)

                return CartViewHolder(binding)
            }
        }

        fun bind(item: Book, isCartItem: Boolean,clickOnCartButton:(bookId: String)->Unit, clickOnWishButton:(bookId:String)->Unit) {
            binding.book = item
            if (isCartItem) {
                binding.addToCartButtonRecycler.visibility = INVISIBLE
            }

            binding.addToCartButtonRecycler.setOnClickListener {
                clickOnCartButton(item.bookId)

            }

            binding.deleteFromListButton.setOnClickListener {
                clickOnWishButton(item.bookId)
            }

            Log.e("binded item", "bind: ${item.bookTitle}", )


            val imagePath = "file:///storage/emulated/0/Android/data/com.hfad.online_bookstore/files/Pictures/image_${item.bookId}.jpg"
            Picasso.get().load(imagePath).into(binding.imageViewCover)

//            val coroutineScope = CoroutineScope(Dispatchers.Main)
//
//            coroutineScope.launch {
//                val mapper = CartToBookMapper()
//
//
//
//
//            }

        }
    }
}