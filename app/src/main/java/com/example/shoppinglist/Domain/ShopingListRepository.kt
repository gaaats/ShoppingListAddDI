package com.example.shoppinglist.Domain

import androidx.lifecycle.LiveData

interface ShopingListRepository {
    fun addItemToList(buyItem: BuyItem)
    fun edit(buyItem: BuyItem)
    fun getShopingList(): LiveData<List<BuyItem>>
    fun deleteItem(buyItem: BuyItem)
    fun take(idOfItem: Int): BuyItem
}