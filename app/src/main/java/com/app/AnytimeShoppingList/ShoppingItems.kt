package com.app.AnytimeShoppingList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="shopping_items" )
data class ShoppingItems (

    @ColumnInfo(name="ItemName")
    var itemName:String,

    @ColumnInfo(name="ItemQuantity")
    var itemQuantity:Int,

    @ColumnInfo(name ="ItemPrice")
    var itemPrice:Int,

    )
{
    @PrimaryKey(autoGenerate =true)
    var id:Int? =null
}