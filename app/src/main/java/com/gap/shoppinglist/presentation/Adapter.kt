package com.gap.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gap.shoppinglist.R
import com.gap.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var count = 1
    var shopItemList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("AdapterTestViewHolder", "onCreateViewHolder: ${count++}")
        val view : View = when (viewType) {
            TYPE_ENABLED -> {
                LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled, parent, false)
            }
            TYPE_DISABLED -> {
                LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
            }
            else -> {
                throw RuntimeException("Not using type $viewType")
            }
        }
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.name)
        val tvCount = view.findViewById<TextView>(R.id.count)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val shopItem = shopItemList[position]
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()


    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopItemList[position]
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