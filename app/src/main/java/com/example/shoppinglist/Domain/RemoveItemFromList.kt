package com.example.shoppinglist.Domain

class RemoveItemFromList(private val shopingListRepository: ShopingListRepository) {
    suspend fun deleteItem(buyItem: BuyItem) {
        shopingListRepository.deleteItem(buyItem)
    }
}