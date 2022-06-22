package com.example.shoppinglist.Domain.usecase

import com.example.shoppinglist.Domain.repository.ShopingListRepository
import com.example.shoppinglist.Domain.model.BuyItem

class RemoveItemFromList(private val shopingListRepository: ShopingListRepository) {
    suspend fun deleteItem(buyItem: BuyItem) {
        shopingListRepository.deleteItem(buyItem)
    }
}