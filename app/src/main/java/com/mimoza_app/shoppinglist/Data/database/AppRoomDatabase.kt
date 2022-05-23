package com.mimoza_app.shoppinglist.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mimoza_app.shoppinglist.Domain.ShopItem

@Database(entities = [ShopItem::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun getAppRoomDao():AppRoomDao

    companion object{
        @Volatile  // database не будет кэшироваться
        private var database:AppRoomDatabase? = null

        @Synchronized // запрещаем чтобы к ней могли обращаться несколько экземпляров одновременно
        fun getInstance(context: Context):AppRoomDatabase{
            if(database == null){
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                return database as AppRoomDatabase
            }else{
                return database as AppRoomDatabase
            }
        }
    }
}