package com.example.shoppinglist.Domain

class TakeItemFromShopingList(private val shopingListRepository: ShopingListRepository) {
    suspend fun take(idOfItem: Int): BuyItem {
       return shopingListRepository.take(idOfItem)
    }
}