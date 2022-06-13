package com.example.shoppinglist.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopListDAO {

    @Query("SELECT * FROM shop_items")
    fun getShopList (): LiveData<List<ShopItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItemToDB (shopItemDBModel: ShopItemDBModel)

    @Query("DELETE FROM shop_items WHERE id=:shopItemDBModelID")
    fun deleteShopItem (shopItemDBModelID: Int)

    @Query("SELECT * FROM shop_items WHERE id=:shopItemDBModelID LIMIT 1")
    fun getSingleItem(shopItemDBModelID: Int):ShopItemDBModel
}