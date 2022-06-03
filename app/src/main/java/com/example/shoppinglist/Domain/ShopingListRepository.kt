package com.example.shoppinglist.Domain

import com.example.shoppinglist.Data.BuyItem

interface ShopingListRepository {
    fun addItemToList(buyItem: BuyItem)
    fun edit(idOfItem: Int)
    fun getShopingList(): List<BuyItem>
    fun deleteItem(buyItem: BuyItem)
    fun take(idOfItem: Int): BuyItem
}