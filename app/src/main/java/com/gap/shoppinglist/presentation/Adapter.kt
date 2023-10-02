package com.gap.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gap.shoppinglist.R
import com.gap.shoppinglist.domain.ShopItem

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val list = listOf<ShopItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled, parent, false)
        return ViewHolder(view)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.name)
        val tvCount = view.findViewById<TextView>(R.id.count)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val shopItem = list[position]
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}