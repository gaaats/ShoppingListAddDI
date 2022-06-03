package com.example.shoppinglist.Data

import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.Domain.ShopingListRepository
import java.lang.RuntimeException

class ShopingListRepositoryImpl : ShopingListRepository {
    private val shopingList = mutableListOf<BuyItem>()
    private var autoIncrement = 0

    override fun addItemToList(buyItem: BuyItem) {
        if (buyItem.id == BuyItem.DEFAULT_INDEX) {
            buyItem.id = autoIncrement++
        }
        shopingList.add(buyItem)
    }

    override fun edit(buyItem: BuyItem) {
        val oldElement = take(buyItem.id)
        shopingList.remove(oldElement)
        addItemToList(buyItem)
    }

    override fun getShopingList(): List<BuyItem> {
        return shopingList.toList()
    }

    override fun deleteItem(buyItem: BuyItem) {
        shopingList.remove(buyItem)
    }

    override fun take(idOfItem: Int): BuyItem {
        return shopingList.find { it.id == idOfItem }
            ?: throw RuntimeException("there is no such element in List")
    }
}