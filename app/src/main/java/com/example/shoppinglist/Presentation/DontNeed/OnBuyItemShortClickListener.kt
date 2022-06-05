package com.example.shoppinglist.Presentation.DontNeed

import com.example.shoppinglist.Domain.BuyItem

interface OnBuyItemShortClickListener {
    fun shortPressedOnItem(buyItem: BuyItem)
}

//не треба бо використав лямбда фун в ShopListAdapter