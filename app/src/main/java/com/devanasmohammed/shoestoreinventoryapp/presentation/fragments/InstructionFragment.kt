package com.devanasmohammed.shoestoreinventoryapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devanasmohammed.shoestoreinventoryapp.R
import com.devanasmohammed.shoestoreinventoryapp.databinding.FragmentInstructionBinding

/**
 * InstructionFragment used to provide some useful instructions
 * for the user about how to use the app
 */
class InstructionFragment : Fragment() {

    private var _binding: FragmentInstructionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_instruction, container, false)
        hideToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navigate to ShoeListFragment
        binding.letsGoBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_instructionFragment_to_shoeListFragment
            )
        }
    }

    /**
     * Clear generated class for data binding
     * to release memory
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * This function used to hide Toolbar for this fragment
     */
    private fun hideToolbar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }


}