package com.gap.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.gap.shoppinglist.domain.DomainRepository
import com.gap.shoppinglist.domain.ShopItem

class RepositoryImpl(application: Application) : DomainRepository {


    private val shopItemDao = AppDatbase.getInstance(application).shopItemDao()
    private val mapper = Mapper()


    override suspend fun addShopItem(shopItem: ShopItem) =
        shopItemDao.addShopItem(mapper.shopItemToShopItemEntity(shopItem))


    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopItemDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopItemDao.addShopItem(mapper.shopItemToShopItemEntity(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val state = shopItemDao.getShopItem(shopItemId)
        return mapper.shopItemEntityToShopItem(state)
    }

    override fun getListShopItem(): LiveData<List<ShopItem>> = shopItemDao.getListShopItem().map {
        mapper.mapListShopItemEntityToShopItem(it)
    }


}