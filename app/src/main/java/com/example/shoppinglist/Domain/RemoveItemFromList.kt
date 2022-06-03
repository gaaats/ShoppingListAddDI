package com.example.shoppinglist.Domain

class RemoveItemFromList(private val shopingListRepository: ShopingListRepository) {
    fun deleteItem(buyItem: BuyItem) {
        shopingListRepository.deleteItem(buyItem)
    }
}