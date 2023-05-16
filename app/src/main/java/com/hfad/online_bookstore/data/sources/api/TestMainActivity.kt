package com.hfad.online_bookstore.data.sources.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.presentation.ui.view.TAG
import kotlinx.coroutines.launch

class TestMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_main)

        var nameBook = ""

        val editText = findViewById<EditText>(R.id.edit_query)
        val findButton = findViewById<AppCompatButton>(R.id.button)


        val textBookTitle = findViewById<TextView>(R.id.text2)
        val textAuthor = findViewById<TextView>(R.id.text4)
        val textIsbn  = findViewById<TextView>(R.id.text6)


        //cam aici e bagat rezultatul in layout

//
//        findButton.setOnClickListener {
//            lifecycleScope.launch {
//                try {
//                    nameBook = editText.text.toString()
//                    val response = RetrofitInstance.api.getResultsByTitle(nameBook)
//                    Log.e(TAG, "Title: ${response.body()!!.docs[0].title}" )
//                    textBookTitle.text = response.body()!!.docs[0].title
//                    textAuthor.text = response.body()!!.docs[0].author_name[0]
//                    textIsbn.text = response.body()!!.docs[0].isbn[0]
//
//                } catch (e: Exception) {
//                    Log.e(TAG, "Error: ${e.message} " )
//                }
//            }
//
//        }
    }
}