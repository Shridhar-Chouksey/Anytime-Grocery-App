package com.app.AnytimeShoppingList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository):ViewModel() {

    fun insert(items: ShoppingItems) =GlobalScope.launch {
        repository.insert(items)
    }
    fun delete(items: ShoppingItems)=GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllShoppingItems()=repository.getAllItems()

}