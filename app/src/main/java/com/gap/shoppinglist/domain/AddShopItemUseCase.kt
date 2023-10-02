package com.gap.shoppinglist.domain

class AddShopItemUseCase(private val repository: DomainRepository) {

    fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem)
    }
}