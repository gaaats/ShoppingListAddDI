package com.example.shoppinglist.Domain

class AddItemToList(private val shopingListRepository: ShopingListRepository) {
    suspend fun addItemToList (buyItem: BuyItem){
        shopingListRepository.addItemToList(buyItem)
    }
}