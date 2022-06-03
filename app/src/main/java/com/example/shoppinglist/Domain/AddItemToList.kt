package com.example.shoppinglist.Domain

import com.example.shoppinglist.Data.BuyItem

class AddItemToList(private val shopingListRepository: ShopingListRepository) {
    fun addItemToList (buyItem: BuyItem){
        shopingListRepository.addItemToList(buyItem)
    }
}