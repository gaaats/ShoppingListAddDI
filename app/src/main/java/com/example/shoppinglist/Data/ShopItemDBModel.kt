package com.example.shoppinglist.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppinglist.Domain.BuyItem

@Entity(tableName = "shop_items")
data class ShopItemDBModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    val total: Int,
    val isBuyed: Boolean,

)
