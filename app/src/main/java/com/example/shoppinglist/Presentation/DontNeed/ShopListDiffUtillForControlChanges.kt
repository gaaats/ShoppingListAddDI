package com.example.shoppinglist.Presentation.DontNeed

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.Domain.BuyItem

class ShopListDiffUtillForControlChanges(
    private val oldList: List<BuyItem>,
    private val newList: List<BuyItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}