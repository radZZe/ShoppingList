package com.mimoza_app.shoppinglist.Presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.shoppinglist.R

class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var tv_content = view.findViewById<TextView>(R.id.contentTV)
    var tv_count = view.findViewById<TextView>(R.id.countTV)
}