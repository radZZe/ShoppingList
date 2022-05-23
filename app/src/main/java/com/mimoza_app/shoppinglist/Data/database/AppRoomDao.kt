package com.mimoza_app.shoppinglist.Data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mimoza_app.shoppinglist.Domain.ShopItem

@Dao
interface AppRoomDao {
    @Query("SELECT * from notes_tables")
    fun getAllNotes(): LiveData<List<ShopItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: ShopItem)

    @Delete
    suspend fun delete(note: ShopItem)

    @Update
    suspend fun update(note:ShopItem)

}