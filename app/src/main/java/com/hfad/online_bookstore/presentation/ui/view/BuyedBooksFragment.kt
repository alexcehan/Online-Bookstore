package com.hfad.online_bookstore.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.databinding.FragmentBuyedBooksBinding
import com.hfad.online_bookstore.presentation.ui.adapter.BuyedBookItemAdapter
import com.hfad.online_bookstore.presentation.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyedBooksFragment : Fragment() {
    private var _binding: FragmentBuyedBooksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyedBooksBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = BuyedBookItemAdapter()
        binding.returnedBookList.adapter = adapter


        viewModel.allBuyedBooks.observe(viewLifecycleOwner, Observer {
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