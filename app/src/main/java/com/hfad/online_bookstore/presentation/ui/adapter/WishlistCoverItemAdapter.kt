package com.hfad.online_bookstore.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.databinding.WishlistCoverCardBinding
import com.squareup.picasso.Picasso
import java.io.File

class WishlistCoverItemAdapter(val clickListener: (book: Book) -> Unit): ListAdapter<Book, WishlistCoverItemAdapter.WishlistItemViewHolder>(BuyedBookDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistItemViewHolder {
        return WishlistItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: WishlistItemViewHolder, position: Int) {
        val item = getItem(position)
        if (checkIfFileExists(item.bookId)) {
            Log.e("Check File","File ${item.bookId} exists! " )
            holder.bind(item,  clickListener)
        } else {
            Log.e("Check File","File ${item.bookId} doesn't exist! " )
        }
    }

    class WishlistItemViewHolder(val binding: WishlistCoverCardBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): WishlistItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WishlistCoverCardBinding.inflate(layoutInflater, parent, false)

                return WishlistItemViewHolder(binding)
            }
        }

        fun bind(item: Book, clickListener: (book: Book) -> Unit) {
            binding.book = item
            binding.root.setOnClickListener{clickListener(item)}

            val imagePath = "file:///storage/emulated/0/Android/data/com.hfad.online_bookstore/files/Pictures/image_${item.bookId}.jpg"
            Picasso.get().load(imagePath).into(binding.wishlistCoverItem)
        }
    }

    fun checkIfFileExists(bookId: String): Boolean {
        val directory = File("/storage/emulated/0/Android/data/com.hfad.online_bookstore/files/Pictures")
        val file = File(directory, "image_${bookId}.jpg")
        return file.exists()
    }
}