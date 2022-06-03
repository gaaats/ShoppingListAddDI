package com.example.shoppinglist.Domain

import com.example.shoppinglist.Data.BuyItem

class RemoveItemFromList(private val shopingListRepository: ShopingListRepository) {
    fun deleteItem(buyItem: BuyItem) {
        shopingListRepository.deleteItem(buyItem)
    }
}