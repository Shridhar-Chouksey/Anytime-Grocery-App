package com.app.AnytimeShoppingList

class ShoppingRepository(private val db:ShoppingDatabase) {

      suspend fun insert(items: ShoppingItems)=db.getShoppingDao().insert(items)
      suspend fun delete(items: ShoppingItems)=db.getShoppingDao().delete(items)

      fun getAllItems() = db.getShoppingDao().getAllShoppingItems()
}