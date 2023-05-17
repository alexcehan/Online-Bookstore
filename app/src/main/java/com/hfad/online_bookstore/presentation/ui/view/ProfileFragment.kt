package com.hfad.online_bookstore.presentation.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hfad.online_bookstore.databinding.FragmentProfileBinding
import com.hfad.online_bookstore.presentation.ui.adapter.BuyedBookItemWithCoverAdapter
import com.hfad.online_bookstore.presentation.ui.adapter.WishlistCoverItemAdapter
import com.hfad.online_bookstore.presentation.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = BuyedBookItemWithCoverAdapter()
        binding.returnedBuyedList.adapter = adapter

        val wishlistAdapter = WishlistCoverItemAdapter()
        binding.wishlistItemsRecyler.adapter = wishlistAdapter

        viewModel.allBuyedBooks.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it.size > 4) {
                    adapter.submitList(it.subList(0, 5))
                } else {
                    adapter.submitList(it)
                }


            }
        })


        viewModel.countAllBuyedBooks.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.numberOfBuyedBooks.text = it.toString()
            } ?: run {
                binding.numberOfBuyedBooks.text = "0"
            }
        })

        viewModel.allWishlistItems.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.size > 5) {
                    wishlistAdapter.submitList(it.subList(0, 6))
                } else {
                    wishlistAdapter.submitList(it)
                }

            }
        })

        viewModel.countAllWishlistBooks.observe(viewLifecycleOwner, Observer {

            it?.let {
                binding.numberOfWishlistBooks.text = it.toString()
            } ?: run {
                binding.numberOfWishlistBooks.text = "0"
            }

        })




        binding.viewAllPurchasedBooksLink.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragment3ToBuyedBooksFragment()
            this.findNavController().navigate(action)

        }

        binding.viewAllWishlistBooksLink.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragment3ToWishlistFragment2()
            this.findNavController().navigate(action)
        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}