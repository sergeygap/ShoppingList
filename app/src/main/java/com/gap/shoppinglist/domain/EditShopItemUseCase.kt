package com.gap.shoppinglist.domain

class EditShopItemUseCase(private val repository: DomainRepository) {
    fun editShopItem(shopItem: ShopItem) {
        repository.editShopItem(shopItem)
    }
}