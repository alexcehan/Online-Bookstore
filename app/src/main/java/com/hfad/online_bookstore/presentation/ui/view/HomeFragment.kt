package com.hfad.online_bookstore.presentation.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController
import com.hfad.online_bookstore.Application
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.data.entities.User
import com.hfad.online_bookstore.data.sources.api.RetrofitInstance
import com.hfad.online_bookstore.data.sources.db.BooksLocalDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val TAG = "API"

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        Log.d("HomeFrag", "Fragment Inflated")

        val application = requireNotNull(this.activity).application
        val dao = BooksLocalDatabase.getInstance(application).bookDbDao





        val convWithFriends = view.findViewById<LinearLayout>(R.id.conversation_with_friends)


        convWithFriends.setOnClickListener {
            Log.d("clicked!", "Movie clicked")
//            val navController = view.findNavController()
//            navController.navigate(R.id.action_homeFragment_to_bookFragment2)


            GlobalScope.launch(Dispatchers.IO) {
                try {


                    val response = RetrofitInstance.api.getResultsByTitle("amintiri din copilarie")
                    Log.e(TAG, "${response.body()!!.docs[0].title} - ${response.body()!!.docs[0].author_name}" )

                } catch (e: Exception) {
                    Log.e(TAG, "${e.message} " )
                }
            }


        }




        return view
    }

}