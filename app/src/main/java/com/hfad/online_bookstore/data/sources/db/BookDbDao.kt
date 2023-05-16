package com.hfad.online_bookstore.data.sources.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.hfad.online_bookstore.data.entities.*

@Dao
interface BookDbDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Insert
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cart: Cart)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wishlist: Wishlist)

    @Insert
    suspend fun insert(buyedBook: BuyedBook)

    @Insert
    suspend fun insertAll(buyedBookList: List<BuyedBook>)

    @Query("SELECT * FROM users WHERE userId=:userId")
    fun getUser(userId: Long): LiveData<User>

    @Query("SELECT * FROM books WHERE bookId=:bookId")
    fun getBook(bookId: String): LiveData<Book>

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM shopping_cart WHERE cartId=:cartId")
    fun getCartEntry(cartId: Long): LiveData<Cart>

    @Query("SELECT * FROM books INNER JOIN shopping_cart ON books.bookId = shopping_cart.bookId")
    fun getAllCartEntries(): LiveData<List<Book>>

    @Query("SELECT * FROM wishlist WHERE wishlistId=:wishlistId")
    fun wishlistEntry(wishlistId: Long): LiveData<Wishlist>

    @Query("SELECT * FROM books INNER JOIN wishlist ON books.bookId = wishlist.bookId")
    fun getAllWishlistEntries(): LiveData<List<Book>>

    @Query("DELETE FROM wishlist WHERE bookid=:bookId")
    fun deleteWishListEntry(bookId: String)

    @Query("DELETE FROM shopping_cart WHERE bookid=:bookId")
    fun deleteCartEntry(bookId: String)

    @Query("select sum(price) from books inner join shopping_cart on books.bookId = shopping_cart.bookId")
    fun getTotalToPayCart(): LiveData<Double>

    @Query("DELETE FROM shopping_cart")
    fun deleteAllEntriesInShoppingCart()

    @Query("SELECT * FROM books INNER JOIN buyed_books ON books.bookId = buyed_books.bookId")
    fun getAllThePurchasedBooks(): LiveData<List<Book>>




}