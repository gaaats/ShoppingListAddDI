package com.example.shoppinglist.Domain.usecase

import com.example.shoppinglist.Domain.repository.ShopingListRepository
import com.example.shoppinglist.Domain.model.BuyItem

class EditItemInShopingList (private val shopingListRepository: ShopingListRepository) {
    suspend fun edit(buyItem: BuyItem){
        shopingListRepository.edit(buyItem)
    }
}