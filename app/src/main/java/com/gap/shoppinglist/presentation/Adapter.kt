package com.gap.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gap.shoppinglist.R
import com.gap.shoppinglist.domain.ShopItem

class Adapter : ListAdapter<ShopItem, ViewHolder>(ShopListDiffUtilItemCallback()) {
    private var count = 1
    var setOnLongClickListener: ((ShopItem) -> Unit)? = null
    var setOnClickListener: ((ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = when (viewType) {
            TYPE_ENABLED -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shop_enabled, parent, false)
            }

            TYPE_DISABLED -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shop_disabled, parent, false)
            }

            else -> {
                throw RuntimeException("Not using type $viewType")
            }
        }
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("AdapterTestViewHolder", "onBindViewHolder: ${count++}")
        val shopItem = getItem(position)
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.itemView.setOnLongClickListener {
            setOnLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.itemView.setOnClickListener {
            setOnClickListener?.invoke(shopItem)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        val itemType = if (item.enabled) {
            TYPE_ENABLED
        } else {
            TYPE_DISABLED
        }
        return itemType
    }

    companion object {
        const val TYPE_DISABLED = -1
        const val TYPE_ENABLED = 1
        const val MAX_POOL_SIZE = 15
    }
}