package com.example.shoppinglist.Presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.Domain.model.BuyItem
import com.example.shoppinglist.R
import com.example.shoppinglist.Presentation.ShopListDiffUtillForListAdapter as ShopListDiffUtillForListAdapter

//class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopingListVievHolder>() {
class ShopListAdapter : ListAdapter<BuyItem, ShopingListVievHolder>(ShopListDiffUtillForListAdapter()) {

    var onBuyItemShortClickListener: ((BuyItem) -> Unit)? = null
    var onBuyItemLongClickListener: ((BuyItem) -> Unit)? = null

//    var listOfItemToBuy = listOf<BuyItem>()
//        set(value) {
//            val diffUtil = ShopListDiffUtillForControlChanges(field, value)
//            DiffUtil.calculateDiff(diffUtil).also {
//                it.dispatchUpdatesTo(this)
//            }
//            field = value
//        }

//    class ShopingListVievHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val binding = ItemToBuyActiveBinding.bind(view)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopingListVievHolder {
        val currentLayout = when (viewType) {
            NEED_TO_BUY -> R.layout.item_to_buy_active
            else -> R.layout.item_to_buy_not_active
        }
        val view = LayoutInflater.from(parent.context).inflate(currentLayout, parent, false)
        return ShopingListVievHolder(view)
    }

    override fun onBindViewHolder(holder: ShopingListVievHolder, position: Int) {
        val currentElement = getItem(position)
        holder.binding.apply {
            tvNameOfItem.text = currentElement.name
            tvSumOfItem.text = currentElement.total.toString()
            cardViev.setOnLongClickListener {
                onBuyItemLongClickListener?.invoke(currentElement)
                true
            }
            cardViev.setOnClickListener {
                onBuyItemShortClickListener?.invoke(currentElement)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isBuyed) {
            ALREADY_BOUGHT
        } else {
            NEED_TO_BUY
        }
    }

    companion object {
        const val NEED_TO_BUY = 55
        const val ALREADY_BOUGHT = 77
    }
}