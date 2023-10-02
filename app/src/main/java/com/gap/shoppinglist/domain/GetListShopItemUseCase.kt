package com.gap.shoppinglist.domain

class GetListShopItemUseCase(private val repository: DomainRepository) {
    fun getListShopItem(): List<ShopItem> {
        return repository.getListShopItem()
    }
}