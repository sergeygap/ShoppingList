package com.gap.shoppinglist.domain

class EditShopItemUseCase(private val repository: DomainRepository) {
    suspend fun editShopItem(shopItem: ShopItem) {
        repository.editShopItem(shopItem)
    }
}