package com.example.shoppinglist.Domain.usecase

import com.example.shoppinglist.Domain.repository.ShopingListRepository
import com.example.shoppinglist.Domain.model.BuyItem

class AddItemToList(private val shopingListRepository: ShopingListRepository) {
    suspend fun addItemToList (buyItem: BuyItem){
        shopingListRepository.addItemToList(buyItem)
    }
}