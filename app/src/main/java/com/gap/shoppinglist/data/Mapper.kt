package com.gap.shoppinglist.data

import com.gap.shoppinglist.domain.ShopItem

class Mapper {

    fun shopItemToShopItemEntity(shopItem: ShopItem): ShopItemDBEntity {
        return ShopItemDBEntity(
            shopItem.id,
            shopItem.name,
            shopItem.count,
            shopItem.enabled
        )
    }

    fun shopItemEntityToShopItem(shopItemEntity: ShopItemDBEntity): ShopItem {
        return ShopItem(
            shopItemEntity.name,
            shopItemEntity.count,
            shopItemEntity.enabled,
            shopItemEntity.id,
        )
    }

    fun mapListShopItemEntityToShopItem(shopItemEntityList: List<ShopItemDBEntity>) : List<ShopItem> {
        return shopItemEntityList.map {
            shopItemEntityToShopItem(it)
        }
    }

}