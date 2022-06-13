package com.example.shoppinglist.Domain

class EditItemInShopingList (private val shopingListRepository: ShopingListRepository) {
    suspend fun edit(buyItem: BuyItem){
        shopingListRepository.edit(buyItem)
    }
}