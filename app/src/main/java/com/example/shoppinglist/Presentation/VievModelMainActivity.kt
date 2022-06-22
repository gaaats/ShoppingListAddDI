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
import javax.inject.Inject

class VievModelMainActivity @Inject constructor(
    private val getShoppingList: GetsShopingList,
    private val deleteItemFromShopingList: RemoveItemFromList,
    private val editItemInShopingList: EditItemInShopingList,
    private val takeItemFromShopingList: TakeItemFromShopingList
) : ViewModel() {


    private val _shoppingList: MutableLiveData<List<BuyItem>> =
        getShoppingList.getShopingList() as MutableLiveData<List<BuyItem>>

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