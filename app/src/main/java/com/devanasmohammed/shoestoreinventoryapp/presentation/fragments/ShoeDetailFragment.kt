package com.devanasmohammed.shoestoreinventoryapp.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devanasmohammed.shoestoreinventoryapp.R
import com.devanasmohammed.shoestoreinventoryapp.data.models.Shoe
import com.devanasmohammed.shoestoreinventoryapp.databinding.FragmentShoeDetailBinding
import com.devanasmohammed.shoestoreinventoryapp.presentation.MainActivity
import com.devanasmohammed.shoestoreinventoryapp.presentation.ShoeSharedViewModel
import com.devanasmohammed.shoestoreinventoryapp.util.Utils
import com.google.android.material.snackbar.Snackbar

/**
 * This class is used for adding a shoe entry
 */
class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoeSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        //save a new shoe and add it to the list
        //then hide keyboard if no focus
        //then navigate back
        binding.saveBtn.setOnClickListener {
            if (isTextFieldValid()) {
                viewModel.addShoe(getEnteredShoe())
            }
            Utils().hideKeyboard(requireActivity())
            findNavController().popBackStack()
        }

        //cancel adding a new shoe and navigate back
        //to ShoeListFragment
        binding.cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }



    private fun isTextFieldValid(): Boolean {
        if (binding.nameEt.text.trim().isEmpty() ||
            binding.sizeEt.text.trim().isEmpty() ||
            binding.companyEt.text.trim().isEmpty() ||
            binding.descriptionEt.text.trim().isEmpty()
        ) {
            Snackbar.make(
                binding.root,
                resources.getString(R.string.fill_fields),
                Snackbar.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }


    /**
     * This method return the entered shoe by user
     */
    private fun getEnteredShoe(): Shoe {
        return Shoe(
            binding.nameEt.text.toString(),
            binding.sizeEt.text.toString().toDouble(),
            binding.companyEt.text.toString(),
            binding.descriptionEt.text.toString(),
        )
    }

}