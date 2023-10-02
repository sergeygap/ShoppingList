package com.gap.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gap.shoppinglist.domain.DomainRepository
import com.gap.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

object RepositoryImpl : DomainRepository {
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            addShopItem(ShopItem("$i", i, false))
        }
    }


    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == -1){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        deleteShopItem(shopItem)
        val newElement = shopItem.copy(enabled = !shopItem.enabled)
        addShopItem(newElement)
        // возможно error
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("ShopItem does not found $shopItemId")
    }

    override fun getListShopItem(): LiveData<List<ShopItem>> {
        return shopListLD
    }
    private fun updateList() {
        shopListLD.value = shopList
    }
}