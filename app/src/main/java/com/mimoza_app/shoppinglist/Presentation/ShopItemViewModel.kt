package com.mimoza_app.shoppinglist.Presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private var _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem



    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val count = parseCount(inputCount)
        val name = parseName(inputName)
        val validData = isValidData(name, count)
        if (validData) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }

    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val count = parseCount(inputCount)
        val name = parseName(inputName)
        val validData = isValidData(name, count)
        if (validData) {
            _shopItem.value?.let {
                val shopItem = it.copy(name,count)
                editShopItemUseCase.editShopItem(shopItem)
                finishWork()
            }
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
        var result = true;
        if ((name.isBlank())) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName() {
        _errorInputName.value = false;
    }

    public fun resetErrorInputCount() {
        _errorInputCount.value = false;
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}