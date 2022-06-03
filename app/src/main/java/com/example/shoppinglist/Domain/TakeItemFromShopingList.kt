package com.example.shoppinglist.Domain

import com.example.shoppinglist.Data.BuyItem

class TakeItemFromShopingList(private val shopingListRepository: ShopingListRepository) {
    fun take(idOfItem: Int): BuyItem {
       return shopingListRepository.take(idOfItem)
    }
}