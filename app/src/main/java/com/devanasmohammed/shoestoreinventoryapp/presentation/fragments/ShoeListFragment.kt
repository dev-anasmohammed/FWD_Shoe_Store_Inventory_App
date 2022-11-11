package com.devanasmohammed.shoestoreinventoryapp.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.devanasmohammed.shoestoreinventoryapp.R
import com.devanasmohammed.shoestoreinventoryapp.data.models.Shoe
import com.devanasmohammed.shoestoreinventoryapp.databinding.FragmentShoeListBinding
import com.devanasmohammed.shoestoreinventoryapp.databinding.ItemShoeBinding
import com.devanasmohammed.shoestoreinventoryapp.presentation.MainActivity
import com.devanasmohammed.shoestoreinventoryapp.presentation.ShoeSharedViewModel
import com.devanasmohammed.shoestoreinventoryapp.util.Constants.Companion.IS_USER_LOGGED_IN
import com.devanasmohammed.shoestoreinventoryapp.util.Utils


/**
 * This class used to show a list of shoes and user can add new shoe
 * by navigating to AddShoeDetailFragment
 */
class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoeSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        //show toolbar because its hidden in the previous fragments
        showToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setUpMenu()

        //observe the list of shoes if a new shoe added it will be updated
        //as long as app running
        viewModel.listOfShoes.observe(viewLifecycleOwner) {
            showListOfShoes(it)
        }

        //navigate to add new shoe
        binding.addShoeBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_shoeListFragment_to_shoeDetailFragment
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
     * This method used to get LinearLayout that we will add items to it
     * by find the parent layout
     * then bind the layout with shoe data class
     * then addView to the parent layout
     * then loop to all list
     */
    private fun showListOfShoes(listOfShoes: List<Shoe>) {
        val parent = binding.shoesList
        for (shoe in listOfShoes) {
            val shoesBinding: ItemShoeBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_shoe,
                parent,
                false
            )
            shoesBinding.shoe = shoe
            parent.addView(shoesBinding.root)
        }
    }

    /**
     * This function used to setup the menu using addMenuProvider() API in a Fragment
     */
    private fun setUpMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu item
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // if clicked logout then save that user is not logged it anymore
                //then navigate user to login fragment
                if (menuItem.itemId == R.id.logout) {
                    Utils().saveData(
                        requireContext(),
                        IS_USER_LOGGED_IN, false
                    )
                    findNavController().navigate(
                        R.id.action_shoeListFragment_to_loginFragment
                    )
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    /**
     * This function used to show Toolbar for this fragment
     */
    private fun showToolbar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}