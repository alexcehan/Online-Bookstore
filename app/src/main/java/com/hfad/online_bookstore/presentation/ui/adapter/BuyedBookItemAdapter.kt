package com.hfad.online_bookstore.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.databinding.BuyedBookCardBinding

class BuyedBookItemAdapter: ListAdapter<Book, BuyedBookItemAdapter.BuyedBookViewHolder>(BuyedBookDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyedBookViewHolder {
        return BuyedBookViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: BuyedBookViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class BuyedBookViewHolder(val binding: BuyedBookCardBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {


            fun inflateFrom(parent: ViewGroup) : BuyedBookViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BuyedBookCardBinding.inflate(layoutInflater, parent, false)

                return BuyedBookViewHolder(binding)
            }
        }

        fun bind(item: Book, position: Int) {
            binding.book = item
            binding.position = position + 1
        }
    }
}