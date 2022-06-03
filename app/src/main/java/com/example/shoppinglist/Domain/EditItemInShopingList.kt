package com.example.shoppinglist.Domain

class EditItemInShopingList (private val shopingListRepository: ShopingListRepository) {
    fun edit(buyItem: BuyItem){
        shopingListRepository.edit(buyItem)
    }
}