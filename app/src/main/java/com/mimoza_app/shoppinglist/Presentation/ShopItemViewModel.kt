package com.mimoza_app.shoppinglist.Presentation

import androidx.lifecycle.ViewModel
import com.mimoza_app.shoppinglist.Data.ShopListRepositoryImpl
import com.mimoza_app.shoppinglist.Domain.AddShopItemUseCase
import com.mimoza_app.shoppinglist.Domain.EditShopItemUseCase
import com.mimoza_app.shoppinglist.Domain.GetShopItemUseCase
import com.mimoza_app.shoppinglist.Domain.ShopItem
import java.lang.RuntimeException

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl
    private var getShopItemUseCase = GetShopItemUseCase(repository)
    private var editShopItemUseCase = EditShopItemUseCase(repository)
    private var addShopItemUseCase = AddShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int): ShopItem {
        return getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val count = parseCount(inputCount)
        val name = parseName(inputName)
        val validData = isValidData(name, count)
        if (validData) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }

    }

    fun editShopItem(shopItem: ShopItem) {
        val name = shopItem.name
        val count = shopItem.count
        val validData = isValidData(name,count)
        if(validData){
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: throw RuntimeException("Некорректное имя ${inputName}")
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun isValidData(name: String, count: Int): Boolean {
        return (name.isBlank()) and (count >= 0)
    }
}