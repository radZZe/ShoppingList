package com.mimoza_app.shoppinglist.Domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(id:Int):ShopItem{
        return shopListRepository.getShopItem(id)
    }
}