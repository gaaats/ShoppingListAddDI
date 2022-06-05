package com.example.shoppinglist.Presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.ShopingListRepositoryImpl
import com.example.shoppinglist.Domain.*

class VievModelMainActivity : ViewModel() {

    private val shopingListRepositoryImpl = ShopingListRepositoryImpl()

    private val getShoppingList = GetsShopingList(shopingListRepositoryImpl)
    private val deleteItemFromShopingList = RemoveItemFromList(shopingListRepositoryImpl)
    private val editItemInShopingList = EditItemInShopingList(shopingListRepositoryImpl)
    private val takeItemFromShopingList = TakeItemFromShopingList(shopingListRepositoryImpl)

    //
    private val shoppingList_:MutableLiveData<List<BuyItem>> = getShoppingList.getShopingList() as MutableLiveData<List<BuyItem>>
//    val shoppingList_ = getShoppingList.getShopingList()
    val shoppingList: LiveData<List<BuyItem>> = shoppingList_

    fun deleteBuyItemFromList(buyItem: BuyItem) {
        deleteItemFromShopingList.deleteItem(buyItem)

    }

    fun changeItemEnableToAnouther(buyItem: BuyItem) {
        val itemForEditing = buyItem.copy(isBuyed = !buyItem.isBuyed)
        editItemInShopingList.edit(itemForEditing)


    }
}