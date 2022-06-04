package com.example.shoppinglist.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val vievModelMainActivity: VievModelMainActivity by viewModels()
    lateinit var shopListAdapter: ShopListAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createAdapter()
        createMethodSwipeAndDelete()
        addOnClickListenerToFAB()

        vievModelMainActivity.shoppingList.observe(this) {
            shopListAdapter.listOfItemToBuy = it
        }
    }

    private fun addOnClickListenerToFAB() {
        binding.floatActionBotom.setOnClickListener {
            Toast.makeText(this, "pressed add", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createMethodSwipeAndDelete() {
        val callBack = object :
            SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemSwiped = shopListAdapter.listOfItemToBuy[viewHolder.adapterPosition]
                vievModelMainActivity.deleteBuyItemFromList(itemSwiped)
            }
        }
        ItemTouchHelper(callBack).apply {
            attachToRecyclerView(binding.recVievHolder)
        }
    }

    private fun createAdapter() {
        shopListAdapter = ShopListAdapter()
        addShortAndLongCLickListener()
        binding.recVievHolder.apply {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.NEED_TO_BUY, POOL_MAX_SIZE)
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.ALREADY_BOUGHT, POOL_MAX_SIZE)
        }
    }

    private fun addShortAndLongCLickListener() {
        shopListAdapter.apply {
            onBuyItemLongClickListener = {
                vievModelMainActivity.changeItemEnableToAnouther(it)
            }
            onBuyItemShortClickListener = {
                Toast.makeText(this@MainActivity, "Buy: ${it.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val POOL_MAX_SIZE = 20
    }

}