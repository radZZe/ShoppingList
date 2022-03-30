package com.mimoza_app.shoppinglist.Domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem:ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}