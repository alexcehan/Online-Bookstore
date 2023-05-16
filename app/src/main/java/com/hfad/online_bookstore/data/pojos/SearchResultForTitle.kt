package com.hfad.online_bookstore.data.pojos

//clasa asta si clasa Docx le-am generat automat cu 'Kotlin data class file from JSON' folosind
// JSON-ul generat  in postman

data class SearchResultForTitle(
    val docs: List<BookEntity>,
    val numFound: Int,
    val numFoundExact: Boolean,
    val num_found: Int,
    val offset: Any,
    val q: String,
    val start: Int
)