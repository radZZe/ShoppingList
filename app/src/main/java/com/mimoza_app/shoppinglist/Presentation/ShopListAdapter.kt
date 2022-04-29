package com.mimoza_app.shoppinglist.Presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mimoza_app.shoppinglist.Domain.ShopItem
import com.mimoza_app.shoppinglist.R

class ShopListAdapter :ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()) {


    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener :((ShopItem)->Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view:View
        val layout = when(viewType){
            ENABLED_LAYOUT -> R.layout.item_shop_enabled
            DISABLED_LAYOUT -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type ${viewType}")
        }
        view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        viewHolder.tv_content.text = shopItem.name
        viewHolder.tv_count.text = shopItem.count.toString()
        viewHolder.view.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
    }


    override fun getItemViewType(position: Int): Int {
        if(getItem(position).active){
            return ENABLED_LAYOUT
        }else{
            return DISABLED_LAYOUT
        }
    }


    companion object{
        const val ENABLED_LAYOUT = 100
        const val DISABLED_LAYOUT = 101
        const val MAX_POOL_SIZE = 15
    }

}