package com.hfad.online_bookstore.presentation.ui.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hfad.online_bookstore.R
import com.hfad.online_bookstore.databinding.FragmentBookBinding
import com.hfad.online_bookstore.presentation.ui.viewmodel.BookDetailsViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : Fragment() {
    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val bookEntity = BookFragmentArgs.fromBundle(requireArguments()).bookEntity
        viewModel.bookEntity.value = bookEntity

        Picasso.get()
            .load("https://covers.openlibrary.org/b/isbn/${bookEntity.isbn?.get(0)}-M.jpg")
            .transform(object : Transformation {

                override fun transform(source: Bitmap): Bitmap {
                    if (source.byteCount == 4) {
                        source.recycle()
                        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                    }
                    return source
                }

                override fun key(): String {
                    return "check_response"
                }
            })
            .error(R.drawable.noimageavailablejpg)
            .into(binding.imageViewBookPoster)

        binding.addToWishlistButton.setOnClickListener {
            viewModel.addBookToWishlist(requireContext())
            viewModel.saveImageToLocal(binding.imageViewBookPoster, requireContext())
        }

        binding.addToCartButton.setOnClickListener {
            viewModel.addBookToCart(requireContext())
            viewModel.saveImageToLocal(binding.imageViewBookPoster, requireContext())
        }



        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })




        return view
    }










    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}