package com.hfad.online_bookstore.data.mappers

import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.data.entities.Cart
import com.hfad.online_bookstore.data.entities.Wishlist

class BookToCartMapper {

    fun mapper(book: Book): Cart {
        return Cart(0L,
            1L,
            book.bookId,
            "image")
    }
}