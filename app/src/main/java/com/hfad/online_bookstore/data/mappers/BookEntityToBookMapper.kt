package com.hfad.online_bookstore.data.mappers

import com.hfad.online_bookstore.data.entities.Book
import com.hfad.online_bookstore.data.pojos.BookEntity

class BookEntityToBookMapper() {

    fun mapper(bookEntity: BookEntity) : Book {

        return Book(
            bookEntity.isbn!!.get(0),
            bookEntity.title,
            bookEntity.author_name[0], //select only the first author in the list
            bookEntity.first_publish_year?: generateYear(),
            bookEntity.ratings_average?: generateRating(), //if rating is null, will generate a fake one
            bookEntity.price?:generatePrice()  //generate a fake random price
        )

    }


    private fun generatePrice(): Double {
        val min = 10.0
        val max = 25.0
        return min + (max - min) * Math.random()
    }

    private fun generateRating(): Double {
        val min = 1.0
        val max = 5.0
        return min + (max - min) * Math.random()
    }

    private fun generateYear(): Int {
        val min = 1900
        val max = 2010
        return (min..max).random()
    }
}