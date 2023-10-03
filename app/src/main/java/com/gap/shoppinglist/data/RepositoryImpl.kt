package com.gap.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gap.shoppinglist.domain.DomainRepository
import com.gap.shoppinglist.domain.ShopItem
import java.lang.RuntimeException
import kotlin.random.Random

object RepositoryImpl : DomainRepository {
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            addShopItem(ShopItem("Name $i", i, Random.nextBoolean()))
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
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
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
        shopListLD.value = shopList.toList()
    }
}