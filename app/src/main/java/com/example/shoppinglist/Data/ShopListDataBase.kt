package com.example.shoppinglist.Data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDBModel::class], version = 1, exportSchema = false)
abstract class ShopListDataBase: RoomDatabase() {

    abstract fun getShopListDAO (): ShopListDAO

    companion object{

        private var INSTANCE: ShopListDataBase? = null
        private val LOCK = Any()
        private const val DATA_BASE_NAME = "shop_item.db"

        fun getDataBase(application: Application): ShopListDataBase{
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    ShopListDataBase::class.java,
                    DATA_BASE_NAME
                )
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}