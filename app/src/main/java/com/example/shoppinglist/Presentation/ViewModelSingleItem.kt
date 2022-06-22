package com.example.shoppinglist.Presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.shoppinglist.Data.ShopingListRepositoryImpl
import com.example.shoppinglist.Domain.usecase.AddItemToList
import com.example.shoppinglist.Domain.model.BuyItem
import com.example.shoppinglist.Domain.usecase.EditItemInShopingList
import com.example.shoppinglist.Domain.usecase.TakeItemFromShopingList
import kotlinx.coroutines.launch

class ViewModelSingleItem(application: Application) : AndroidViewModel(application) {

    private val _errorInputNameLD = MutableLiveData<Boolean>()
    val errorInputNameLD: LiveData<Boolean> = _errorInputNameLD

    private val _errorInputNumberLD = MutableLiveData<Boolean>()
    val errorInputNumberLD: LiveData<Boolean> = _errorInputNumberLD

    private val _buyItemFromGet = MutableLiveData<BuyItem>()
    val buyItemFromGet: LiveData<BuyItem> = _buyItemFromGet

    private val _canAppCloseScrnSingleItem = MutableLiveData<Unit>()
    val canAppCloseScrnSingleItem: LiveData<Unit> = _canAppCloseScrnSingleItem

    val shopListRepImpl = ShopingListRepositoryImpl(application)

    private val getItemShopListSingle = TakeItemFromShopingList(shopListRepImpl)
    private val editItemShopListSingle = EditItemInShopingList(shopListRepImpl)
    private val addItemShopListSingle = AddItemToList(shopListRepImpl)

    fun getItemOnActivitySingle(buyItemId: Int) {
        viewModelScope.launch {
            _buyItemFromGet.value = getItemShopListSingle.take(buyItemId)
        }
    }

    fun addItemOnActivitySingle(inputString: String?, inputNumber: String?) {
        val name = parseName(inputString)
        val count = parseNumber(inputNumber)
        val isValid = validating(name, count)
        if (isValid) {
            val elementToAdd = BuyItem(name, count, true)
            viewModelScope.launch {
                addItemShopListSingle.addItemToList(elementToAdd)
            }
            appCanCloseScreen()
        }
    }

    fun editItemOnActivitySingle(inputString: String?, inputNumber: String?) {

        val nameAfterParse = parseName(inputString)
        val countAfterParse = parseNumber(inputNumber)
        val isValid = validating(nameAfterParse, countAfterParse)
        if (isValid) {
            buyItemFromGet.value?.let {
                val curElem = it.copy(name = nameAfterParse, total = countAfterParse)
                viewModelScope.launch {
                    editItemShopListSingle.edit(curElem)
                }
            }
            appCanCloseScreen()
        }
    }


    private fun validating(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputNameLD.value = true

        }
        if (count <= 0) {
            result = false
            _errorInputNumberLD.value = true
        }
        return result
    }

    private fun parseNumber(inputNumber: String?): Int {
        return try {
            inputNumber?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun parseName(inputString: String?): String {
        return inputString?.trim() ?: ""
    }

    fun cleanErrorInputName() {
        _errorInputNameLD.value = false
    }

    fun cleanErrorInputNumber() {
        _errorInputNumberLD.value = false
    }

    private fun appCanCloseScreen() {
        _canAppCloseScrnSingleItem.value = Unit
    }
}