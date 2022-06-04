package com.example.shoppinglist.Presentation

import com.example.shoppinglist.Domain.BuyItem

interface OnBuyItemLongClickListener {
    fun pressedOnItem(buyItem: BuyItem)
}