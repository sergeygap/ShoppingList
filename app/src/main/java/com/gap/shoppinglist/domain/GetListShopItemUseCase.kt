package com.gap.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetListShopItemUseCase(private val repository: DomainRepository) {
    fun getListShopItem(): LiveData<List<ShopItem>> {
        return repository.getListShopItem()
    }
}