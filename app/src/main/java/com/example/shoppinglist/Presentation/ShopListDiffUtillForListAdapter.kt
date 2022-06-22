package com.example.shoppinglist.Presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.Domain.model.BuyItem

class ShopListDiffUtillForListAdapter: DiffUtil.ItemCallback<BuyItem>() {
    override fun areItemsTheSame(oldItem: BuyItem, newItem: BuyItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BuyItem, newItem: BuyItem): Boolean {
        return oldItem == newItem
    }

}