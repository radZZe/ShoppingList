package com.mimoza_app.shoppinglist.Domain

interface ShopListRepository{
    fun getShopList():List<ShopItem>
    fun getShopItem(id:Int):ShopItem
    fun addShopItem(shopItem:ShopItem)
    fun editShopItem(shopItem:ShopItem)
    fun deleteShopItem(shopItem:ShopItem)
}