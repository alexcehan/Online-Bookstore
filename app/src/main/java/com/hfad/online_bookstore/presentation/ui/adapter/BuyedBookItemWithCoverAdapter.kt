package com.hfad.online_bookstore.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.databinding.BuyedBookCardWithCoverBinding
import com.squareup.picasso.Picasso

class BuyedBookItemWithCoverAdapter: ListAdapter<Book, BuyedBookItemWithCoverAdapter.BuyedBookWithCoverViewHolder>(BuyedBookDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuyedBookWithCoverViewHolder {
        return BuyedBookWithCoverViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: BuyedBookWithCoverViewHolder, position: Int) {
        Log.e("Item with cover", "start of onBindViewHolder position: ${position}" )
        val item = getItem(position)
        holder.bind(item)
    }

    class BuyedBookWithCoverViewHolder(val binding: BuyedBookCardWithCoverBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup) : BuyedBookWithCoverViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BuyedBookCardWithCoverBinding.inflate(layoutInflater, parent, false)

                return BuyedBookWithCoverViewHolder(binding)
            }
        }

        fun bind(item: Book) {
            Log.e("Item with cover", "Start of the bind method:  ${item.bookId}" )
            binding.book = item

            val imagePath = "file:///storage/emulated/0/Android/data/com.hfad.online_bookstore/files/Pictures/image_${item.bookId}.jpg"
            Picasso.get().load(imagePath).into(binding.buyedBookCardCover)

        }


    }


}