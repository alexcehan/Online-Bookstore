package com.hfad.online_bookstore.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hfad.online_bookstore.databinding.FragmentProfileBinding
import com.hfad.online_bookstore.presentation.ui.adapter.BuyedBookItemAdapter
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

        binding.viewAllPurchasedBooksLink.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragment3ToBuyedBooksFragment()
            this.findNavController().navigate(action)

        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}