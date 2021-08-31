package com.app.AnytimeShoppingList

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

//Anytime Shopping List : To-do & Grocery App
class MainActivity : AppCompatActivity() ,ShoppingRVAdapter.ShoppingItemClickInterface {
    lateinit var itemsRV: RecyclerView
    lateinit var FloatingBT: FloatingActionButton
    lateinit var list: List<ShoppingItems>
    lateinit var shoppingRVAdapter: ShoppingRVAdapter
    lateinit var shoppingViewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

             itemsRV=findViewById(R.id.idRVitems)
             FloatingBT=findViewById(R.id.FloatingBT)

             list= ArrayList<ShoppingItems>()
             shoppingRVAdapter =ShoppingRVAdapter(list,this)
             itemsRV.layoutManager=LinearLayoutManager(this)
             itemsRV.adapter=shoppingRVAdapter
             val shoppingRepository=ShoppingRepository(ShoppingDatabase(this))
             val factory =ShoppingViewModelFactory(shoppingRepository)
             shoppingViewModel=ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)
             shoppingViewModel.getAllShoppingItems().observe(this, {

                 shoppingRVAdapter.list = it
                 shoppingRVAdapter.notifyDataSetChanged()
             })


           FloatingBT.setOnClickListener{
             openDialog()
           }

    }

     fun openDialog() {

         val dialog=Dialog(this)
         dialog.setContentView(R.layout.shopping_add_dialog)
         val cancelBtn =dialog.findViewById<Button>(R.id.idBtnCancel)
         val addBtn =dialog.findViewById<Button>(R.id.idBtnAdd)
         val itemEdit =dialog.findViewById<EditText>(R.id.idEditItemName)
         val itemPriceEdit =dialog.findViewById<EditText>(R.id.idEditItemPrice)
         val itemQuantityEdit =dialog.findViewById<EditText>(R.id.idEditItemQuantity)

         cancelBtn.setOnClickListener {
             dialog.dismiss()
         }

         addBtn.setOnClickListener {

         val itemName : String =itemEdit.text.toString()
         val itemPrice :String=itemPriceEdit.text.toString()
             val itemQuantity :String=itemQuantityEdit.text.toString()

         val qty: Int= itemQuantity.toInt()
         val pr: Int= itemPrice.toInt()

             if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()) {
                 val items = ShoppingItems(itemName, qty, pr)
                 shoppingViewModel.insert(items)
                 Toast.makeText(applicationContext, "Item Inserted..", Toast.LENGTH_SHORT).show()
                 shoppingRVAdapter.notifyDataSetChanged()
                 dialog.dismiss()
             }
             else{
                 Toast.makeText(applicationContext,"Please enter all the data..",Toast.LENGTH_SHORT).show()
             }

         }
         dialog.show()

    }

    override fun onItemClick(shoppingItems: ShoppingItems) {
           shoppingViewModel.delete(shoppingItems)
           shoppingRVAdapter.notifyDataSetChanged()
         Toast.makeText(applicationContext,"Item Deleted..",Toast.LENGTH_SHORT).show()

    }
}