package com.example.shoppinglist.Presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.ShopingListRepositoryImpl
import com.example.shoppinglist.Domain.*

class VievModelMainActivity : ViewModel() {

    private val shoppingList_ = MutableLiveData<List<BuyItem>>()
    val shoppingList: LiveData<List<BuyItem>> = shoppingList_

    private val shopingListRepositoryImpl = ShopingListRepositoryImpl()

    private val getShoppingList = GetsShopingList(shopingListRepositoryImpl)
    private val deleteItemFromShopingList = RemoveItemFromList(shopingListRepositoryImpl)
    private val editItemInShopingList = EditItemInShopingList(shopingListRepositoryImpl)

    fun getList() {
        shoppingList_.value = getShoppingList.getShopingList()
    }

    fun deleteBuyItemFromList(buyItem: BuyItem){
        deleteItemFromShopingList.deleteItem(buyItem)
        getList()

    }
    fun editItemEnableOrNot (buyItem: BuyItem){
        var itemForEditing = buyItem.copy(isBuyed = !buyItem.isBuyed)
        editItemInShopingList.edit(itemForEditing)
        getList()


    }
}