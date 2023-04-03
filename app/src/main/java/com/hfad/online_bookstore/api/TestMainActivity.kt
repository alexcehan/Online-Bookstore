package com.hfad.online_bookstore.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.gui.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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


        findButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    nameBook = editText.text.toString()
                    val response = RetrofitInstance.api.getResultsByTitle(nameBook)
                    Log.e(TAG, "Title: ${response.body()!!.docs[0].title}" )
                    textBookTitle.text = response.body()!!.docs[0].title
                    textAuthor.text = response.body()!!.docs[0].author_name[0]
                    textIsbn.text = response.body()!!.docs[0].isbn[0]

                } catch (e: Exception) {
                    Log.e(TAG, "Error: ${e.message} " )
                }
            }

        }
    }
}