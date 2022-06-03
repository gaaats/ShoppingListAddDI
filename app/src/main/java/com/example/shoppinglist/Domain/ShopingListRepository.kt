package com.example.shoppinglist.Domain

interface ShopingListRepository {
    fun addItemToList(buyItem: BuyItem)
    fun edit(buyItem: BuyItem)
    fun getShopingList(): List<BuyItem>
    fun deleteItem(buyItem: BuyItem)
    fun take(idOfItem: Int): BuyItem
}