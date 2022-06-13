package com.example.shoppinglist.Domain

import androidx.lifecycle.LiveData

interface ShopingListRepository {
    suspend fun addItemToList(buyItem: BuyItem)
    suspend fun edit(buyItem: BuyItem)
    suspend fun deleteItem(buyItem: BuyItem)
    suspend fun take(idOfItem: Int): BuyItem
    fun getShopingList(): LiveData<List<BuyItem>>
}