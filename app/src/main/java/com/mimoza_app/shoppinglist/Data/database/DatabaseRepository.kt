package com.mimoza_app.shoppinglist.Data.database

import androidx.lifecycle.LiveData
import com.mimoza_app.shoppinglist.Domain.ShopItem

interface DatabaseRepository {
    val allItem: LiveData<List<ShopItem>>
    suspend fun insert(note: ShopItem, onSuccess: () -> Unit)
    suspend fun delete(note: ShopItem, onSuccess: () -> Unit)
    suspend fun update(note:ShopItem, onSuccess: () -> Unit)
}