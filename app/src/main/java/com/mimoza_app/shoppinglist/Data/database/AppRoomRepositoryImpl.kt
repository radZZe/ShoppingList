package com.mimoza_app.shoppinglist.Data.database

import androidx.lifecycle.LiveData
import com.mimoza_app.shoppinglist.Domain.ShopItem

class AppRoomRepositoryImpl(private val appRoomDao: AppRoomDao):DatabaseRepository {
    override val allItem: LiveData<List<ShopItem>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: ShopItem, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: ShopItem, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override suspend fun update(note: ShopItem, onSuccess: () -> Unit) {
        appRoomDao.update(note)
        onSuccess()
    }

}