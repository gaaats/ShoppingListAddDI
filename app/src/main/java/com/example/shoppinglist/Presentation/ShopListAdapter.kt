package com.example.shoppinglist.Presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ItemToBuyActiveBinding

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopingListVievHolder>() {


    var listOfItemToBuy = listOf<BuyItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ShopingListVievHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemToBuyActiveBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopingListVievHolder {
        val currentLayout = when (viewType) {
            NEED_TO_BUY -> R.layout.item_to_buy_active
            else -> R.layout.item_to_buy_not_active
        }
        val view = LayoutInflater.from(parent.context).inflate(currentLayout, parent, false)
        return ShopingListVievHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ShopingListVievHolder, position: Int) {
        holder.binding.tvNameOfItem.text = listOfItemToBuy[position].name
        holder.binding.tvSumOfItem.text = listOfItemToBuy[position].total.toString()
    }

    override fun getItemCount(): Int {
        return listOfItemToBuy.size
    }

    override fun getItemViewType(position: Int): Int {
        val resultType = if (listOfItemToBuy[position].isBuyed) {
            ALREADY_BOUGHT
        } else {
            NEED_TO_BUY
        }
        return resultType
    }

    companion object {
        const val NEED_TO_BUY = 55
        const val ALREADY_BOUGHT = 77
    }
}