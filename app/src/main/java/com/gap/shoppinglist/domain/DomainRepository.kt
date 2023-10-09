package com.gap.shoppinglist.domain

import androidx.lifecycle.LiveData

interface DomainRepository {
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItem(shopItemId: Int): ShopItem
    fun getListShopItem(): LiveData<List<ShopItem>>

}