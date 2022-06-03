package com.example.shoppinglist.Domain

class EditItemInShopingList (private val shopingListRepository: ShopingListRepository) {
    fun edit(idOfItem: Int){
        shopingListRepository.edit(idOfItem)
    }
}