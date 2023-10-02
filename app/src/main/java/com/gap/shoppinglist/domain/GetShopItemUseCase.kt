package com.gap.shoppinglist.domain

class GetShopItemUseCase(private val repository: DomainRepository) {
    fun getShopItem(shopItemId: Int): ShopItem {
       return repository.getShopItem(shopItemId)
    }
}