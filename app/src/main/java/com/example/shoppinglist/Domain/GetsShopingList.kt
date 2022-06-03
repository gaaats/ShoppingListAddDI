package com.example.shoppinglist.Domain

import androidx.lifecycle.LiveData

class GetsShopingList (private val shopingListRepository: ShopingListRepository) {
    fun getShopingList(): LiveData<List<BuyItem>> {
        return shopingListRepository.getShopingList()
    }
}