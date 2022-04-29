package com.mimoza_app.shoppinglist.Domain

import androidx.lifecycle.LiveData

interface ShopListRepository{
    fun getShopList(): LiveData<List<ShopItem>>
    fun getShopItem(id:Int):ShopItem
    fun addShopItem(shopItem:ShopItem)
    fun editShopItem(shopItem:ShopItem)
    fun deleteShopItem(shopItem:ShopItem)
}