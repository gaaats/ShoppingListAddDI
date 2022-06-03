package com.example.shoppinglist.Domain

class AddItemToList(private val shopingListRepository: ShopingListRepository) {
    fun addItemToList (buyItem: BuyItem){
        shopingListRepository.addItemToList(buyItem)
    }
}