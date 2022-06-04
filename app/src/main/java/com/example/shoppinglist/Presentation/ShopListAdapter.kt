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


    class ShopingListVievHolder (view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemToBuyActiveBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopingListVievHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_to_buy_active, parent, false)
        return ShopingListVievHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ShopingListVievHolder, position: Int) {
        holder.binding.tvNameOfItem.text = listOfItemToBuy[position].name
        holder.binding.tvSumOfItem.text = listOfItemToBuy[position].total.toString()
        if (listOfItemToBuy[position].isBuyed){
            holder.binding.cardViev.setBackgroundColor(R.color.for_non_active_card)
        } else{
            holder.binding.cardViev.setBackgroundColor(R.color.teal_200)
        }
    }

    override fun getItemCount(): Int {
        return listOfItemToBuy.size
    }
}