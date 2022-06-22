package com.example.shoppinglist.Domain.usecase

import androidx.lifecycle.LiveData
import com.example.shoppinglist.Domain.repository.ShopingListRepository
import com.example.shoppinglist.Domain.model.BuyItem

class GetsShopingList (private val shopingListRepository: ShopingListRepository) {
    fun getShopingList(): LiveData<List<BuyItem>> {
        return shopingListRepository.getShopingList()
    }
}