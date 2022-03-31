package com.mimoza_app.shoppinglist.Presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mimoza_app.shoppinglist.Data.ShopListRepositoryImpl
import com.mimoza_app.shoppinglist.Data.ShopListRepositoryImpl.deleteShopItem
import com.mimoza_app.shoppinglist.Domain.DeleteShopItemUseCase
import com.mimoza_app.shoppinglist.Domain.EditShopItemUseCase
import com.mimoza_app.shoppinglist.Domain.GetShopListUseCase
import com.mimoza_app.shoppinglist.Domain.ShopItem

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl
    val shopList = MutableLiveData<List<ShopItem>>()

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()

    }


    fun changeStateItem(shopItem: ShopItem){
        var newItem = shopItem.copy(active = !shopItem.active)
        editShopItemUseCase.editShopItem(shopItem)
        getShopList()
    }
}