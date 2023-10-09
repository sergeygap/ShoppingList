package com.gap.shoppinglist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gap.shoppinglist.data.RepositoryImpl
import com.gap.shoppinglist.domain.DeleteShopItemUseCase
import com.gap.shoppinglist.domain.EditShopItemUseCase
import com.gap.shoppinglist.domain.GetListShopItemUseCase
import com.gap.shoppinglist.domain.GetShopItemUseCase
import com.gap.shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getListShopItemUseCase = GetListShopItemUseCase(repository)
    private val getShopItemIseCase = GetShopItemUseCase(repository)

    val shopListLiveData = getListShopItemUseCase.getListShopItem()

    private val scope = CoroutineScope(Dispatchers.IO)


     fun deleteShopItem(shopItemId: Int) {
        scope.launch {
            deleteShopItemUseCase.deleteShopItem(getShopItemIseCase.getShopItem(shopItemId))
        }
    }

     fun changeEnableState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}