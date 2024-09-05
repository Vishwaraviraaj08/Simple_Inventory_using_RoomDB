package com.example.testapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.Model.Item
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemViewModel = ItemViewModel(application)

        val addItemButton = findViewById<Button>(R.id.addItemButton)
        addItemButton.setOnClickListener {
            startActivity(Intent(this, AddItemActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val items : List<Item> = itemViewModel.getItems()
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter = ItemAdapter(items)
        }
    }
}