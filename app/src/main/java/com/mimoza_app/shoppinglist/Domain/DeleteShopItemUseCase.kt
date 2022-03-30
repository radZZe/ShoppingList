package com.mimoza_app.shoppinglist.Domain

class DeleteShopItem(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem:ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}