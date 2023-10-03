package com.gap.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.gap.shoppinglist.domain.ShopItem

class ShopListDiffUtillItemCallback() : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}