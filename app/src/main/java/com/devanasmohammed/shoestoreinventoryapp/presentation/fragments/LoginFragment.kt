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
import com.devanasmohammed.shoestoreinventoryapp.databinding.FragmentLoginBinding
import com.devanasmohammed.shoestoreinventoryapp.presentation.MainActivity
import com.devanasmohammed.shoestoreinventoryapp.presentation.ShoeSharedViewModel
import com.devanasmohammed.shoestoreinventoryapp.util.Constants.Companion.IS_USER_LOGGED_IN
import com.devanasmohammed.shoestoreinventoryapp.util.Utils

/**
 * LoginFragment used to Login and Create Account
 * Using your email address and password
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: ShoeSharedViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var loginViewType = "Login"
    private var createViewType = "Create Account"
    private var viewTypes = listOf(loginViewType, createViewType)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLoggedIn()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        hideToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        //observe the change between auth types login or create account
        viewModel.index.observe(viewLifecycleOwner) { index ->
            binding.titleTv.text = viewTypes[index]
            binding.authBtn.text = viewTypes[index]
            if (index == 1) { //Create
                binding.authTv.text = viewTypes[index - 1]
                binding.preAuthTv.text = resources.getString(R.string.already_have_account)
            } else { //Login
                binding.authTv.text = viewTypes[index + 1]
                binding.preAuthTv.text = resources.getString(R.string.don_t_have_account)
            }
        }

        //toggle between create or login account
        binding.authTv.setOnClickListener {
            toggleBetweenLoginAndCreate()
        }

        //save that user is logged in and navigate to WelcomeFragment
        binding.authBtn.setOnClickListener {
            Utils().saveData(requireContext(), IS_USER_LOGGED_IN, true)
            findNavController().navigate(
                R.id.action_loginFragment_to_welcomeFragment
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
     * This method used to toggle between creating account or
     * login with an account by change the text of textViews
     */
    private fun toggleBetweenLoginAndCreate() {
        viewModel.changeAuthType()
    }

    /**
     * This method used to check if user logged in or not
     * if logged in save the login state
     */
    private fun isLoggedIn() {
        //get isUserLoggedIn or not from shared Preferences
        val isLoggedIn = Utils().loadBoolean(
            requireContext(),
            IS_USER_LOGGED_IN, false
        )
        //if logged in navigate to ShoreListFragment
        if (isLoggedIn == true) {
            findNavController().navigate(
                R.id.action_loginFragment_to_shoeListFragment
            )
        }
    }

    /**
     * This function used to hide Toolbar for this fragment
     */
    private fun hideToolbar(){
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }


}