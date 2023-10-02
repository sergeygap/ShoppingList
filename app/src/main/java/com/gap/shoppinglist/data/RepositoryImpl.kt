package com.gap.shoppinglist.data

import com.gap.shoppinglist.domain.DomainRepository
import com.gap.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

object RepositoryImpl : DomainRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == -1){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        deleteShopItem(shopItem)
        addShopItem(shopItem)
        // возможно error
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("ShopItem does not found $shopItemId")
    }

    override fun getListShopItem(): List<ShopItem> {
        return shopList.toList()
    }
}