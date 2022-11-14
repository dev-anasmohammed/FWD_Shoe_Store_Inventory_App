package com.devanasmohammed.shoestoreinventoryapp.presentation

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devanasmohammed.shoestoreinventoryapp.BR
import com.devanasmohammed.shoestoreinventoryapp.data.models.Shoe

/**
 * This a sharedViewModel used along  the app
 */

class ShoeSharedViewModel : ViewModel(), Observable {

    private val propertyChangeRegistry = PropertyChangeRegistry()

    private var _index = MutableLiveData(0)
    val index: LiveData<Int> get() = _index

    private var _listOfShoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val listOfShoes: LiveData<MutableList<Shoe>> get() = _listOfShoes

    @Bindable
    var savedShoe = Shoe()
        set(value) {
            if (value != field) {
                field = value
                propertyChangeRegistry.notifyChange(this, BR.shoe)
            }
        }

    /**
     * Change the index of the auth types
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
    fun addShoe(enteredShoe: Shoe) {
        val list = _listOfShoes.value
        list?.add(enteredShoe)
        _listOfShoes.postValue(list!!)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }
}