package com.mimoza_app.shoppinglist.Domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_tables")
data class ShopItem(
    @ColumnInfo var name: String,
    @ColumnInfo var count: Int,
    @ColumnInfo val active: Boolean,
    @PrimaryKey(autoGenerate = true) var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}