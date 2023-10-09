package com.gap.shoppinglist.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gap.shoppinglist.data.RepositoryImpl
import com.gap.shoppinglist.domain.DeleteShopItemUseCase
import com.gap.shoppinglist.domain.EditShopItemUseCase
import com.gap.shoppinglist.domain.GetListShopItemUseCase
import com.gap.shoppinglist.domain.GetShopItemUseCase
import com.gap.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getListShopItemUseCase = GetListShopItemUseCase(repository)
    private val getShopItemIseCase = GetShopItemUseCase(repository)

    val shopListLiveData = getListShopItemUseCase.getListShopItem()



     fun deleteShopItem(shopItemId: Int) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(getShopItemIseCase.getShopItem(shopItemId))
        }
    }

     fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }


}