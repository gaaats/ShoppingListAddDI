package com.example.shoppinglist.Presentation

import com.example.shoppinglist.Domain.model.BuyItem

interface OnBuyItemLongClickListener {
    fun pressedOnItem(buyItem: BuyItem)
}

//не треба бо використав лямбда фун в ShopListAdapter