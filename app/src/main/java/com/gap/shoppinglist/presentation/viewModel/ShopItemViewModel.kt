package com.gap.shoppinglist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gap.shoppinglist.data.RepositoryImpl
import com.gap.shoppinglist.domain.AddShopItemUseCase
import com.gap.shoppinglist.domain.EditShopItemUseCase
import com.gap.shoppinglist.domain.GetShopItemUseCase
import com.gap.shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _getShopItemLD = MutableLiveData<ShopItem>()
    val getShopItemLD: LiveData<ShopItem>
        get() = _getShopItemLD

    private val _exitActivity = MutableLiveData<Unit>()
    val exitActivity: LiveData<Unit>
        get() = _exitActivity

    fun getShopItem(shopItemId: Int) {
        scope.launch {
            val shopItem = getShopItemUseCase.getShopItem(shopItemId)
            _getShopItemLD.value = shopItem
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = trimName(inputName)
        val count = trimCount(inputCount)
        val fieldValid = fieldsValid(name, count)
        if (fieldValid) {
            scope.launch {
                val shopItem = ShopItem(name, count, true)
                addShopItemUseCase.addShopItem(shopItem)
                finishWork()
            }
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = trimName(inputName)
        val count = trimCount(inputCount)
        val fieldValid = fieldsValid(name, count)
        if (fieldValid) {
            _getShopItemLD.value?.let {
                scope.launch {
                    val shopItem = it.copy(name = name, count = count)
                    editShopItemUseCase.editShopItem(shopItem)
                    finishWork()
                }
            }
        }
    }

    private fun fieldsValid(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun trimCount(inputCount: String?): Int = inputCount?.trim()?.toIntOrNull() ?: 0
    private fun trimName(inputName: String?): String = inputName?.trim() ?: ""

    private fun finishWork() {
        _exitActivity.value = Unit
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}