package com.example.shoppinglist.Domain

data class BuyItem(

    val name: String,
    val total: Int,
    var isBuyed: Boolean,
    var id: Int = DEFAULT_INDEX
){
    companion object{
        const val DEFAULT_INDEX = -1
    }
}

