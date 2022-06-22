package com.example.shoppinglist.Domain.usecase

import com.example.shoppinglist.Domain.repository.ShopingListRepository
import com.example.shoppinglist.Domain.model.BuyItem

class TakeItemFromShopingList(private val shopingListRepository: ShopingListRepository) {
    suspend fun take(idOfItem: Int): BuyItem {
       return shopingListRepository.take(idOfItem)
    }
}