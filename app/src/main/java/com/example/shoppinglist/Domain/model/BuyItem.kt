package com.example.shoppinglist.Domain.model

data class BuyItem(

    val name: String,
    val total: Int,
    val isBuyed: Boolean,
    var id: Int = DEFAULT_INDEX
){
    companion object{
        const val DEFAULT_INDEX = 0
    }
}

