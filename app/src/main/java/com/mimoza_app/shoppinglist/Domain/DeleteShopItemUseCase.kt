package com.mimoza_app.shoppinglist.Domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem:ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}