package com.mimoza_app.shoppinglist.Domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList():List<ShopItem>{
        return shopListRepository.getShopList()
    }
}