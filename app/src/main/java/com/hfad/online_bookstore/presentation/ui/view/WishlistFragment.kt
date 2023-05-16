package com.hfad.online_bookstore.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hfad.online_bookstore.databinding.FragmentWishlistBinding
import com.hfad.online_bookstore.presentation.ui.adapter.CartWishlistItemAdapter
import com.hfad.online_bookstore.presentation.ui.viewmodel.ShopAndWishListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShopAndWishListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = CartWishlistItemAdapter(false, {bookId: String -> viewModel.onCartButtonClicked(bookId) }, {bookId: String -> viewModel.onDeleteButtonClick(bookId, false) })

        binding.returnedBookList.adapter = adapter

        viewModel.booksInTheWishlist.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })



        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}