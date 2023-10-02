package com.gap.shoppinglist.domain

import androidx.lifecycle.LiveData

interface DomainRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItem(shopItemId: Int): ShopItem
    fun getListShopItem(): LiveData<List<ShopItem>>

}