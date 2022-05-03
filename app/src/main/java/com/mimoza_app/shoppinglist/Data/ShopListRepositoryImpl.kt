package com.mimoza_app.shoppinglist.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mimoza_app.shoppinglist.Domain.ShopItem
import com.mimoza_app.shoppinglist.Domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl:ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({o1,o2 -> o1.id.compareTo(o2.id)})


    private var autoIncrementId  = 0


    fun updateList(){
        shopListLD.value = shopList.toMutableList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun getShopItem(id: Int): ShopItem {
       return shopList.find { it.id == id } ?: throw RuntimeException("Элемент не найден!!")
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val deleteElem:ShopItem = shopList.find{ it.id == shopItem.id }
            ?: throw RuntimeException("Элемент не найден!!")
        deleteShopItem(deleteElem)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

}