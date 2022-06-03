package com.example.shoppinglist.Domain

import com.example.shoppinglist.Data.BuyItem

class GetsShopingList (private val shopingListRepository: ShopingListRepository) {
    fun getShopingList():List<BuyItem>{
        return shopingListRepository.getShopingList()
    }
}