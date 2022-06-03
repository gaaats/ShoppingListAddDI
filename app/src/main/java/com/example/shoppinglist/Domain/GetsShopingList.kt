package com.example.shoppinglist.Domain

class GetsShopingList (private val shopingListRepository: ShopingListRepository) {
    fun getShopingList():List<BuyItem>{
        return shopingListRepository.getShopingList()
    }
}