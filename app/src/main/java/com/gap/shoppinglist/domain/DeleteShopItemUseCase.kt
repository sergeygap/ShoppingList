package com.gap.shoppinglist.domain

class DeleteShopItemUseCase(private val repository: DomainRepository) {
    suspend fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem)
    }
}