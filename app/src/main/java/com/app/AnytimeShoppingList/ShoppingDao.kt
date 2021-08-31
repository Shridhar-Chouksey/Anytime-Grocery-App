package com.app.AnytimeShoppingList

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao           //Data Access Object
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingItems)

    @Delete
    suspend fun delete(item: ShoppingItems)

    @Query("SELECT * FROM shopping_items")   //Reading all the data
    fun getAllShoppingItems() : LiveData<List<ShoppingItems>>



}