package com.example.shoppinglist.Presentation

import com.example.shoppinglist.Domain.BuyItem

interface OnBuyItemShortClickListener {
    fun shortPressedOnItem(buyItem: BuyItem)
}