package com.devanasmohammed.shoestoreinventoryapp.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.hardware.input.InputManager
import android.view.inputmethod.InputMethodManager
import com.devanasmohammed.shoestoreinventoryapp.util.Constants.Companion.APP_KEY


/**
 * This class used as general utils for the app
 */
class Utils {
    private var sharedPreference: SharedPreferences? = null

    /**
     * This method used to save boolean data to shared preferences
     */
    fun saveData(context: Context, key: String, value: Boolean) {
        if (sharedPreference == null) {
            sharedPreference =
                context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        }
        val editor = sharedPreference?.edit()!!
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * This method used to load boolean data from shared preferences
     */
    fun loadBoolean(
        context: Context,
        key: String,
        defaultVal: Boolean
    ): Boolean? {
        if (sharedPreference == null) {
            sharedPreference =
                context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        }
        return sharedPreference?.getBoolean(key, defaultVal)
    }

    /**
     * This function used to close keyboard when user clicked on the button
     */
    fun hideKeyboard(activity: Activity) {
        //check first if there is not focus
        val view = activity.currentFocus
        //hide the keyboard then
        if(view!=null){
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken , 0)
        }
    }

}