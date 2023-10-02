package com.gap.shoppinglist.domain

class DeleteShopItemUseCase(private val repository: DomainRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem)
    }
}