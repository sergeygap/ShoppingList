package com.gap.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.gap.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    var count = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[(MainViewModel::class.java)]
        viewModel.shopListLiveData.observe(this) {
            Log.d("OnCreateLDTest", "onCreate: $it")
            if (count == 3) {
                count++
                viewModel.editShopItem(2)
            }

        }

        viewModel.deleteShopItem(1)
        viewModel.editShopItem(0)
    }
}