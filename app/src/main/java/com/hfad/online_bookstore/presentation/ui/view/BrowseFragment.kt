package com.hfad.online_bookstore.presentation.ui.view

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.databinding.FragmentBrowseBinding
import com.hfad.online_bookstore.presentation.ui.adapter.BookEntityItemAdapter
import com.hfad.online_bookstore.presentation.ui.viewmodel.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseFragment : Fragment() {
    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = BookEntityItemAdapter{bookIsbn -> viewModel.onBookClicked(bookIsbn)}
        binding.returnedBookList.adapter = adapter

        val searchView = binding.searchView
        val searchEditText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setHintTextColor(ContextCompat.getColor(requireActivity(), R.color.blackLight))
        searchEditText.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))

        val searchMagIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        val searchCloseIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)

        searchMagIcon.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black), PorterDuff.Mode.SRC_IN)
        searchCloseIcon.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black), PorterDuff.Mode.SRC_IN)


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {



                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!!.isNotEmpty()) {
                    viewModel.getBooksByTitle(query!!)
                }

                return true
            }


        })


        viewModel.booksByTitle.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToBookDetails.observe(viewLifecycleOwner, Observer{bookIsbn -> bookIsbn?.let {
            val action = BrowseFragmentDirections.actionBrowseFragment2ToBookFragment2(bookIsbn)
            this.findNavController().navigate(action)
            viewModel.onBookNavigated()

        }})




        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}