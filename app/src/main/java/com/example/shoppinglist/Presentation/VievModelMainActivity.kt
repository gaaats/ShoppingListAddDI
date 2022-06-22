package com.example.shoppinglist.Presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.shoppinglist.Data.ShopingListRepositoryImpl
import com.example.shoppinglist.Domain.model.BuyItem
import com.example.shoppinglist.Domain.usecase.EditItemInShopingList
import com.example.shoppinglist.Domain.usecase.GetsShopingList
import com.example.shoppinglist.Domain.usecase.RemoveItemFromList
import com.example.shoppinglist.Domain.usecase.TakeItemFromShopingList
import kotlinx.coroutines.launch

class VievModelMainActivity(application: Application) : AndroidViewModel(application) {

    private val shopingListRepositoryImpl = ShopingListRepositoryImpl(application)

    private val getShoppingList = GetsShopingList(shopingListRepositoryImpl)
    private val deleteItemFromShopingList = RemoveItemFromList(shopingListRepositoryImpl)
    private val editItemInShopingList = EditItemInShopingList(shopingListRepositoryImpl)
    private val takeItemFromShopingList = TakeItemFromShopingList(shopingListRepositoryImpl)

    private val _shoppingList:MutableLiveData<List<BuyItem>> = getShoppingList.getShopingList() as MutableLiveData<List<BuyItem>>
//    val shoppingList_ = getShoppingList.getShopingList()
    val shoppingList: LiveData<List<BuyItem>> = _shoppingList

    fun deleteBuyItemFromList(buyItem: BuyItem) {
        viewModelScope.launch {
            deleteItemFromShopingList.deleteItem(buyItem)
        }
    }

    fun changeItemEnableToAnouther(buyItem: BuyItem) {
        val itemForEditing = buyItem.copy(isBuyed = !buyItem.isBuyed)
        viewModelScope.launch {
            editItemInShopingList.edit(itemForEditing)
        }
    }
}