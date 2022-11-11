package com.devanasmohammed.shoestoreinventoryapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.devanasmohammed.shoestoreinventoryapp.R
import com.devanasmohammed.shoestoreinventoryapp.databinding.ActivityMainBinding
import timber.log.Timber

/**
 * This is the single activity of the app
 */
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    lateinit var viewModel: ShoeSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        //init viewModel here to be used as SharedViewModel
        viewModel = ViewModelProvider(this)[ShoeSharedViewModel::class.java]

        //setup toolbar with navigation
        setSupportActionBar(_binding?.toolbar)
    }

}