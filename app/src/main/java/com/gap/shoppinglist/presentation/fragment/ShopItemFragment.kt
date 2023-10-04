package com.gap.shoppinglist.presentation.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gap.shoppinglist.R
import com.gap.shoppinglist.domain.ShopItem
import com.gap.shoppinglist.presentation.viewModel.ShopItemViewModel
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment : Fragment() {

    private lateinit var viewModel: ShopItemViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var save: Button

    private var screenMode: String = UNKNOWN_MODE
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setUpView(view)
        when (screenMode) {
            EDIT_MODE -> launchEditMode()
            ADD_MODE -> launchAddMode()
            else -> throw RuntimeException(UNKNOWN_MODE)
        }
    }


    private fun launchAddMode() {
        save.setOnClickListener {
            viewModel.addShopItem(etName.text.toString(), etCount.text.toString())
        }
        setUpLaunch()
    }

    private fun setUpLaunch() {
        setErrors()
        setUpEditTextListeners()
        viewModel.exitActivity.observe(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        viewModel.getShopItemLD.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        save.setOnClickListener {
            viewModel.editShopItem(etName.text.toString(), etCount.text.toString())
        }
        setUpLaunch()
    }

    private fun setErrors() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            if (it) {
                tilName.error = "Error input data"
            } else {
                tilName.error = null
            }
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            if (it) {
                tilCount.error = "Error input data"
            } else {
                tilCount.error = null
            }
        }
    }

    private fun setUpEditTextListeners() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun parseArguments() {

        val args = requireArguments()
        if (!args.containsKey(MODE)) {
            throw RuntimeException("Where is mode?")
        }
        val mode = args.getString(MODE)
        if (mode != ADD_MODE && mode != EDIT_MODE) {
            throw RuntimeException("Mode Unknown")
        }
        screenMode = mode
        if (screenMode == EDIT_MODE) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw RuntimeException("Where id shop item id")
            }
            shopItemId = args.getInt(SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
        if (screenMode == EDIT_MODE && shopItemId == ShopItem.UNDEFINED_ID) {
            throw RuntimeException("Where is id?")
        }

    }

    private fun setUpView(view: View) {
        save = view.findViewById(R.id.save)
        etName = view.findViewById(R.id.et_name)
        etCount = view.findViewById(R.id.et_count)
        tilName = view.findViewById(R.id.til_name)
        tilCount = view.findViewById(R.id.til_count)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
    }

    companion object {
        private const val MODE = "mode"
        private const val EDIT_MODE = "mode_edit"
        private const val ADD_MODE = "mode_add"
        private const val SHOP_ITEM_ID = "shop_item_id"
        private const val UNKNOWN_MODE = ""

        fun newInstanceModeAdd(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(MODE, ADD_MODE)
                }
            }
        }

        fun newInstanceModeEdit(shopItemId: Int): ShopItemFragment {
            val args = Bundle()
            args.putString(MODE, EDIT_MODE)
            args.putInt(SHOP_ITEM_ID, shopItemId)
            val fragment = ShopItemFragment()
            fragment.arguments = args
            return fragment
        }
    }
}