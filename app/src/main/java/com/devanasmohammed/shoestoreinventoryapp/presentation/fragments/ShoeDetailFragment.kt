package com.devanasmohammed.shoestoreinventoryapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate<FragmentShoeDetailBinding?>(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        viewModel = (activity as MainActivity).viewModel

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //to clear all textFields
        viewModel.savedShoe = Shoe()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //save a new shoe and add it to the list
        //then hide keyboard if no focus
        //then navigate back
        binding.saveBtn.setOnClickListener {
            if(isTextFieldValid()){
                viewModel.addShoe(viewModel.savedShoe)
                Utils().hideKeyboard(requireActivity())
                findNavController().popBackStack()
            }
        }

        //cancel adding a new shoe and navigate back
        //to ShoeListFragment
        binding.cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    /**
     * This method used to check is all textFields is not empty
     * to add a valid data
     */
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

}