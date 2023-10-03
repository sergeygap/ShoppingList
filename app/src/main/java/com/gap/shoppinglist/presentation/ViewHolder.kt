package com.gap.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gap.shoppinglist.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.name)
    val tvCount = view.findViewById<TextView>(R.id.count)
}