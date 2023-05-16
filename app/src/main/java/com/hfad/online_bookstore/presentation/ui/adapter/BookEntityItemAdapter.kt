package com.hfad.online_bookstore.presentation.ui.adapter

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.data.pojos.BookEntity
import com.hfad.online_bookstore.databinding.BookCardItemBinding
import com.squareup.picasso.Cache
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import okhttp3.Dispatcher

class BookEntityItemAdapter(val clickListener: (isbn: BookEntity) -> Unit): ListAdapter<BookEntity, BookEntityItemAdapter.BookEntityViewHolder>(BookEntityDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookEntityViewHolder {
        return BookEntityViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: BookEntityViewHolder, position: Int) {
        val item = getItem(position)
        if (item.isbn!=null && item.ratings_average!=null && item.first_publish_year !=null) {
            Log.e("Check Null JSON", "ISBN - ${item.isbn}, Rating Average - ${item.ratings_average}, First Pub Year - ${item.first_publish_year}" )

        }
        if(item.isbn != null) {
            holder.bind(item, clickListener)
        }


        Picasso.get().apply {
            // Clear disk cache
            val cacheField = this@apply.javaClass.getDeclaredField("cache")
            cacheField.isAccessible = true
            val cache = cacheField.get(this@apply) as Cache
            cache.clear()

            // Clear memory cache

        }

    }

    class BookEntityViewHolder(val binding: BookCardItemBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup):BookEntityViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookCardItemBinding.inflate(layoutInflater,  parent, false)

                return BookEntityViewHolder(binding)
            }
        }

        fun bind(item: BookEntity, clickListener: (isbn: BookEntity)-> Unit) {
            if(item.isbn != null) {
                binding.bookEntity = item
                binding.root.setOnClickListener{clickListener(item)}

                Picasso.get()
                    .load("https://covers.openlibrary.org/b/isbn/${item.isbn[0]}-M.jpg")
                    .transform(object : Transformation {

                        override fun transform(source: Bitmap): Bitmap {
                            if (source.byteCount == 4) {
                                try {
                                    val inputStream = itemView.context.assets.open("images/noimageavailablejpg.jpg")
                                    val fallbackImage = BitmapFactory.decodeStream(inputStream)
                                    source.recycle()
                                    return fallbackImage
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                            return source
                        }

                        override fun key(): String {
                            return "check_response"
                        }
                    })
                    .error(R.drawable.noimageavailablejpg)
                    .into(binding.imageViewCover)

            }



        }
    }




}