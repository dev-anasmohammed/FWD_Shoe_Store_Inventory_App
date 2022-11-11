package com.devanasmohammed.shoestoreinventoryapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devanasmohammed.shoestoreinventoryapp.data.models.Shoe

/**
 * This a sharedViewModel used along  the app
 */



class ShoeSharedViewModel : ViewModel() {

    private var _index = MutableLiveData(0)
    val index: LiveData<Int> get() = _index

    private var _listOfShoes = MutableLiveData<MutableList<Shoe>>(
        mutableListOf()
    )
    val listOfShoes: LiveData<MutableList<Shoe>> get() = _listOfShoes

    /**
     * Change the index of the
     */
    fun changeAuthType() {
        val newIndex = _index.value!!
        if (_index.value == 0) {
            _index.postValue(newIndex + 1)
        } else {
            _index.postValue(newIndex - 1)
        }
    }

    /**
     * This method used to add new show to the list
     */
    fun addShoe(shoe: Shoe){
        val list = _listOfShoes.value
        list?.add(shoe)
        _listOfShoes.postValue(list!!)
    }


}