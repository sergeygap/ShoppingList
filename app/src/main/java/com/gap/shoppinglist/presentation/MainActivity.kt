package com.gap.shoppinglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gap.shoppinglist.R
import com.gap.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llScroll: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llScroll = findViewById(R.id.ll_Scroll)
        viewModel = ViewModelProvider(this)[(MainViewModel::class.java)]
        viewModel.shopListLiveData.observe(this) {
            updateList(it)
        }

    }

    private fun updateList(it: List<ShopItem>) {
        llScroll.removeAllViews()
        for (shopItem in it) {
            val layoutId = if (shopItem.enabled) {
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId, llScroll, false)
            val tvName = view.findViewById<TextView>(R.id.name)
            val tvCount = view.findViewById<TextView>(R.id.count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.editShopItem(shopItem.id)
                true
            }
            llScroll.addView(view)
        }

    }
}