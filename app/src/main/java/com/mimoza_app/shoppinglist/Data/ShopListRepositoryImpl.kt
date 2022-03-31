package com.mimoza_app.shoppinglist.Data

import com.mimoza_app.shoppinglist.Domain.ShopItem
import com.mimoza_app.shoppinglist.Domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl:ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name$i",24,true)
            addShopItem(item)
        }
    }

    private var autoIncrementId  = 0

    override fun getShopList(): List<ShopItem> {
        return shopList.toMutableList()
    }

    override fun getShopItem(id: Int): ShopItem {
       return shopList.find { it.id == id } ?: throw RuntimeException("Элемент не найден!!")
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val deleteElem:ShopItem = shopList.find{ it.id == shopItem.id }
            ?: throw RuntimeException("Элемент не найден!!")
        deleteShopItem(deleteElem)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

}