package com.gap.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gap.shoppinglist.R
import com.gap.shoppinglist.domain.ShopItem
import com.gap.shoppinglist.presentation.fragment.ShopItemFragment

class ShopItemActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private var screenMode = UNKNOWN_MODE
    private var shopItemId = ShopItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun parseIntent() {
        shopItemId = intent.getIntExtra(SHOP_ITEM_ID, -1)
        screenMode = intent.getStringExtra(EXTRA_MODE) ?: UNKNOWN_MODE
    }

    private fun launchRightMode() {
        val shopItemFragment = when (screenMode) {
            EDIT_MODE -> ShopItemFragment.newInstanceModeEdit(shopItemId)
            ADD_MODE -> ShopItemFragment.newInstanceModeAdd()
            else -> throw RuntimeException(UNKNOWN_MODE)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, shopItemFragment)
            .commit()
    }


    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val EDIT_MODE = "mode_edit"
        private const val ADD_MODE = "mode_add"
        private const val SHOP_ITEM_ID = "shop_item_id"
        private const val UNKNOWN_MODE = ""

        fun intentFactoryModeEdit(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, EDIT_MODE)
            intent.putExtra(SHOP_ITEM_ID, shopItemId)
            return intent
        }

        fun intentFactoryModeAdd(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, ADD_MODE)
            return intent
        }
    }
}