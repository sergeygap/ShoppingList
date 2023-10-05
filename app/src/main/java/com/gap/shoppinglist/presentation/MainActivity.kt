package com.gap.shoppinglist.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gap.shoppinglist.R
import com.gap.shoppinglist.presentation.Adapter.Companion.MAX_POOL_SIZE
import com.gap.shoppinglist.presentation.Adapter.Companion.TYPE_DISABLED
import com.gap.shoppinglist.presentation.Adapter.Companion.TYPE_ENABLED
import com.gap.shoppinglist.presentation.fragment.ShopItemFragment
import com.gap.shoppinglist.presentation.viewModel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: Adapter
    private lateinit var fab: FloatingActionButton
    private var shopItemContainer: FragmentContainerView? = null
    private var landMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        workInLandMode()
        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[(MainViewModel::class.java)]
        viewModel.shopListLiveData.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun workInLandMode() {
        shopItemContainer = findViewById(R.id.shop_item_container_land)
        shopItemContainer?.let {
            landMode = true
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

            if (landMode) {
                val shopItemFragment = ShopItemFragment.newInstanceModeEdit(it.id)
                beginTransaction(shopItemFragment)
            } else {
                val intent = ShopItemActivity.intentFactoryModeEdit(this, it.id)
                startActivity(intent)
            }


        }
        fab.setOnClickListener {
            if (landMode) {
                val shopItemFragment = ShopItemFragment.newInstanceModeAdd()
                beginTransaction(shopItemFragment)
            } else {
                val intent = ShopItemActivity.intentFactoryModeAdd(this)
                startActivity(intent)
            }

        }
    }

    fun shopToastOnFinishEditing() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun beginTransaction(shopItemFragment: ShopItemFragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container_land, shopItemFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setOnLongClickListener() {
        adapter.setOnLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

}