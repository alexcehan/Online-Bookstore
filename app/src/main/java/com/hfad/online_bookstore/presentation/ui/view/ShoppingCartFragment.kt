package com.hfad.online_bookstore.presentation.ui.view

import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.databinding.FragmentShoppingCartBinding
import com.hfad.online_bookstore.presentation.ui.adapter.CartWishlistItemAdapter
import com.hfad.online_bookstore.presentation.ui.viewmodel.ShopAndWishListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShoppingCartFragment : Fragment() {
    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShopAndWishListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner



        val adapter = CartWishlistItemAdapter(true, {bookId: String -> viewModel.onCartButtonClicked(bookId) }, {bookId: String -> viewModel.onDeleteButtonClick(bookId, true) })
        binding.returnedBookList.adapter = adapter

        viewModel.booksInTheCart.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.totalToPayForCart.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.payCartButton.visibility = VISIBLE
                binding.totalAmountToBePayed.visibility= VISIBLE
                binding.totalToPayHardText.visibility = VISIBLE
            } ?: run {
                binding.payCartButton.visibility = INVISIBLE
                binding.totalAmountToBePayed.visibility= INVISIBLE
                binding.totalToPayHardText.visibility = INVISIBLE

            }
        })

        binding.payCartButton.setOnClickListener {
            viewModel.addCartToPurchasedList()
            this.findNavController().navigate(ShoppingCartFragmentDirections.actionShoppingCartFragmentToPurchaseSuccesfullFragment())
        }








        return view
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//        requireActivity().findViewById<MaterialToolbar>(R.id.top_toolbar).visibility = View.GONE
//    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}