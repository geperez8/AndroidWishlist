package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import listItem

class MainActivity : AppCompatActivity() {

    lateinit var items: ArrayList<listItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Lookup the RecyclerView in activity layout
        val emailsRv = findViewById<RecyclerView>(R.id.wishList)
        // Fetch the list of emails
        items = ArrayList()
        // Create adapter passing in the list of emails
        val adapter = wishListAdapter(items)
        // Attach the adapter to the RecyclerView to populate items
        emailsRv.adapter = adapter
        // Set layout manager to position the items
        emailsRv.layoutManager = LinearLayoutManager(this)

        val enterButton = findViewById<Button>(R.id.submitButton)

        enterButton.setOnClickListener{
            val itemName = findViewById<EditText>(R.id.promptItemName)
            val itemPrice = findViewById<EditText>(R.id.promptItemPrice)
            val itemUrl = findViewById<EditText>(R.id.promptItemUrl)

            val item = listItem(itemName.text.toString(), itemPrice.text.toString(), itemUrl.text.toString())

            itemName.setText("")
            itemPrice.setText("")
            itemUrl.setText("")
            items.add(item)

            adapter.notifyDataSetChanged()
        }
    }




}