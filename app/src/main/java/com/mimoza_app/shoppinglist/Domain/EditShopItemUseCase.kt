package com.mimoza_app.shoppinglist.Domain

class EditShopItem(private val shopListRepository: ShopListRepository) {
    fun editShopItemUseCase(shopItem:ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}