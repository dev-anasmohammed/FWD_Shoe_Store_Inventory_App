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
import com.devanasmohammed.shoestoreinventoryapp.databinding.FragmentWelcomeBinding

/**
 * WelcomeFragment used to welcome the user
 */
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_welcome, container, false)
        hideToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navigate to InstructionFragment
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_welcomeFragment_to_instructionFragment
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