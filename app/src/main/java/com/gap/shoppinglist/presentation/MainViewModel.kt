package com.gap.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gap.shoppinglist.data.RepositoryImpl
import com.gap.shoppinglist.domain.DeleteShopItemUseCase
import com.gap.shoppinglist.domain.EditShopItemUseCase
import com.gap.shoppinglist.domain.GetListShopItemUseCase
import com.gap.shoppinglist.domain.GetShopItemUseCase
import com.gap.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = RepositoryImpl
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getListShopItemUseCase = GetListShopItemUseCase(repository)
    private val getShopItemIseCase = GetShopItemUseCase(repository)

    val shopListLiveData = getListShopItemUseCase.getListShopItem()




    fun deleteShopItem(shopItemId: Int) {
        deleteShopItemUseCase.deleteShopItem(getShopItemIseCase.getShopItem(shopItemId))
    }

    fun editShopItem(shopItemId: Int) {
        editShopItemUseCase.editShopItem(getShopItemIseCase.getShopItem(shopItemId))
    }

}