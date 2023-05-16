package com.hfad.online_bookstore.presentation.ui.viewmodel

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.online_bookstore.data.entities.User
import com.hfad.online_bookstore.data.mappers.BookEntityToBookMapper
import com.hfad.online_bookstore.data.mappers.BookToCartMapper
import com.hfad.online_bookstore.data.mappers.BookToWishlistMapper
import com.hfad.online_bookstore.data.pojos.BookEntity
import com.hfad.online_bookstore.data.pojos.bookbyisbnpackage.BookByIsbnEntity
import com.hfad.online_bookstore.data.sources.db.BookDbDao
import com.hfad.online_bookstore.data.usecases.FetchDataByBookIsbnUseCase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val fetchDataByBookIsbnUseCase: FetchDataByBookIsbnUseCase,
    private val bookDbDao: BookDbDao
    ) :ViewModel() {



        private var _bookByIsbn = MutableLiveData<BookByIsbnEntity>()
        val bookByIsbn = _bookByIsbn

        private var _bookEntity = MutableLiveData<BookEntity>()
        val bookEntity = _bookEntity

        fun getBookByIsbn(bookIsbn: String) {
            viewModelScope.launch {
                fetchDataByBookIsbnUseCase(bookIsbn).collect {
                    result ->
                        if(result.title != "") {
                            _bookByIsbn.value = result
                            Log.e("Book Use Case", "Book Author: ${result.authors[0].key}")
                        }
                }
            }
        }

        fun addBookToCart(context: Context) {

            val bookToCartMapper= BookToCartMapper()
            val bookEntityToBookMapper = BookEntityToBookMapper()

            viewModelScope.launch {
                if (bookEntity.value != null) {
                    val book = bookEntityToBookMapper.mapper(bookEntity.value!!)
                    bookDbDao.insert(book)
                    val wishListEntry = bookToCartMapper.mapper(book)
                    bookDbDao.insert(wishListEntry)
                    Toast.makeText(context, "Book Added to Cart", Toast.LENGTH_LONG).show()


                }
            }
        }

        fun addBookToWishlist(context: Context) {
            val wishlistMapper = BookToWishlistMapper()
            val bookEntityToBookMapper = BookEntityToBookMapper()

            viewModelScope.launch {


                if(bookEntity.value != null) {
                    //bookDbDao.insert(User(0, "alex", "cehan", "alex", "1234"))
                    val book = bookEntityToBookMapper.mapper(bookEntity.value!!)
                    bookDbDao.insert(book)
                    val wishListEntry = wishlistMapper.mapper(book)
                    bookDbDao.insert(wishListEntry)
                    Toast.makeText(context, "Book Added to Wishlist", Toast.LENGTH_LONG).show()

                }


            }


        }

        fun saveImageToLocal(imageView: ImageView, context: Context) {






            val bookIsbn: String? = bookEntity.value?.isbn?.get(0)

            val drawable = imageView.drawable
            if (drawable is BitmapDrawable) {
                val bitmap = drawable.bitmap

                val file = File(
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "image_${bookIsbn}.jpg"
                )

                val fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()

                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(file.absolutePath),
                    arrayOf("image/jpeg"),
                    null
                )

//                Toast.makeText(context, "Image saved to ${file.absolutePath}", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(context, "Unable to save image", Toast.LENGTH_LONG).show()
            }


//            // Get the bitmap from the ImageView
//            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
//            val fileName = "poster_image_${bookIsbn}.png"
//            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
//
//
//
//            // Save the bitmap to the device's external storage
//
//            val directory = File(Environment.getExternalStorageDirectory().absolutePath + "/OnlineBookStoreImages/")
//            Log.e("PIC SAVE", "saveImageToLocal: ${directory.exists()} " )
//            if (!directory.exists()) {
//                Log.e("PIC SAVE", "saveImageToLocal inside: ${directory.mkdirs()} " )
//
//                Log.e("PIC SAVE", "saveImageToLocal inside: ${directory.exists()} " )
//            }
//            //val file = File(directory, fileName)
//            Log.e("PIC SAVE", "saveImageToLocal: ${file}" )
//            try {
//                val fos = FileOutputStream(file)
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
//                fos.flush()
//                fos.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//
//            // Show a toast message to indicate that the image has been saved
//            Toast.makeText(context, "Image saved to MyImages folder", Toast.LENGTH_SHORT).show()


        }




    }