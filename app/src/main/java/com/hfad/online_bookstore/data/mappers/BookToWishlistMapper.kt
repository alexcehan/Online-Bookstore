package com.hfad.online_bookstore.data.mappers

import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.data.entities.Wishlist

class BookToWishlistMapper {

    fun mapper(book: Book): Wishlist {
        return Wishlist(0L,
                           1L,
                            book.bookId,
                            "image")
    }
}