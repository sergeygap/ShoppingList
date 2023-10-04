package com.gap.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gap.shoppinglist.R
import com.gap.shoppinglist.domain.ShopItem
import com.gap.shoppinglist.presentation.viewModel.ShopItemViewModel
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {
    private lateinit var viewModel: ShopItemViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var save: Button

    private var screenMode = UNKNOWN_MODE
    private var shopItemId = ShopItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
//        parseIntent()
//        setUpViewModel()
//        setUpView()
//        when (screenMode) {
//            EDIT_MODE -> launchEditMode()
//            ADD_MODE -> launchAddMode()
//            else -> throw RuntimeException(UNKNOWN_MODE)
//        }
    }
//
//    private fun launchAddMode() {
//        save.setOnClickListener {
//            viewModel.addShopItem(etName.text.toString(), etCount.text.toString())
//        }
//        setUpLaunch()
//    }
//
//    private fun setUpLaunch() {
//        setErrors()
//        setUpEditTextListeners()
//        viewModel.exitActivity.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.getShopItemLD.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        save.setOnClickListener {
//            viewModel.editShopItem(etName.text.toString(), etCount.text.toString())
//        }
//        setUpLaunch()
//    }
//
//    private fun setErrors() {
//        viewModel.errorInputName.observe(this) {
//            if (it) {
//                tilName.error = "Error input data"
//            } else {
//                tilName.error = null
//            }
//        }
//        viewModel.errorInputCount.observe(this) {
//            if (it) {
//                tilCount.error = "Error input data"
//            } else {
//                tilCount.error = null
//            }
//        }
//    }
//
//    private fun setUpEditTextListeners() {
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//    }
//
//    private fun parseIntent() {
//        shopItemId = intent.getIntExtra(SHOP_ITEM_ID, -1)
//        screenMode = intent.getStringExtra(EXTRA_MODE) ?: UNKNOWN_MODE
//        //TODO: may be error because using this code without checking
//    }
//
//    private fun setUpView() {
//        save = findViewById(R.id.save)
//        etName = findViewById(R.id.et_name)
//        etCount = findViewById(R.id.et_count)
//        tilName = findViewById(R.id.til_name)
//        tilCount = findViewById(R.id.til_count)
//    }
//
//    private fun setUpViewModel() {
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//    }

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