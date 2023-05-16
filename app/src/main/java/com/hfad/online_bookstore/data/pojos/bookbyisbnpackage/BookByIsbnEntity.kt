package com.hfad.online_bookstore.data.pojos.bookbyisbnpackage

data class BookByIsbnEntity(
    val authors: List<Author>,
    val by_statement: String,
    val classifications: Classifications,
    val contributions: List<String>,
    val covers: List<Int>,
    val created: Created,
    val identifiers: Identifiers,
    val isbn_10: List<String>,
    val key: String,
    val languages: List<Language>,
    val last_modified: LastModified,
    val latest_revision: Int,
    val lc_classifications: List<String>,
    val local_id: List<String>,
    val ocaid: String,
    val publish_country: String,
    val publish_date: String,
    val publish_places: List<String>,
    val publishers: List<String>,
    val revision: Int,
    val source_records: List<String>,
    val title: String,
    val type: Type,
    val work_titles: List<String>,
    val works: List<Work>
)