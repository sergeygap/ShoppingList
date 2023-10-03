package com.gap.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gap.shoppinglist.R
import com.gap.shoppinglist.presentation.Adapter.Companion.MAX_POOL_SIZE
import com.gap.shoppinglist.presentation.Adapter.Companion.TYPE_DISABLED
import com.gap.shoppinglist.presentation.Adapter.Companion.TYPE_ENABLED

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[(MainViewModel::class.java)]
        viewModel.shopListLiveData.observe(this) {
            adapter.shopItemList = it
        }

    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        this.adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.recycledViewPool.setMaxRecycledViews(TYPE_ENABLED, MAX_POOL_SIZE)
        recyclerView.recycledViewPool.setMaxRecycledViews(TYPE_DISABLED, MAX_POOL_SIZE)
    }

}