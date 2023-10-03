package com.gap.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gap.shoppinglist.R
import com.gap.shoppinglist.presentation.Adapter.Companion.MAX_POOL_SIZE
import com.gap.shoppinglist.presentation.Adapter.Companion.TYPE_DISABLED
import com.gap.shoppinglist.presentation.Adapter.Companion.TYPE_ENABLED
import com.gap.shoppinglist.presentation.viewModel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: Adapter
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[(MainViewModel::class.java)]
        viewModel.shopListLiveData.observe(this) {
            adapter.submitList(it)
        }

    }

    private fun setUpView() {
        fab = findViewById(R.id.FAB)
    }

    private fun setUpRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        this.adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.recycledViewPool.setMaxRecycledViews(TYPE_ENABLED, MAX_POOL_SIZE)
        recyclerView.recycledViewPool.setMaxRecycledViews(TYPE_DISABLED, MAX_POOL_SIZE)
        setOnLongClickListener()
        setOnClickListener()
        setOnSwipeClickListener(recyclerView)
    }

    private fun setOnSwipeClickListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopPosition = viewHolder.adapterPosition
                val shopItem = adapter.currentList[shopPosition]
                viewModel.deleteShopItem(shopItem.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setOnClickListener() {
        adapter.setOnClickListener = {
            Log.d("SetOnClickListener", it.toString())
            val intent = ShopItemActivity.intentFactoryModeEdit(this, it.id)
            startActivity(intent)
        }
        fab.setOnClickListener {
            val intent = ShopItemActivity.intentFactoryModeAdd(this)
            startActivity(intent)
        }
    }

    private fun setOnLongClickListener() {
        adapter.setOnLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

}