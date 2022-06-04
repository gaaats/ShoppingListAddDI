package com.example.shoppinglist.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.R
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

        vievModelMainActivity.shoppingList.observe(this) {

            shopListAdapter.listOfItemToBuy = it
        }
    }

    private fun createAdapter() {
        shopListAdapter = ShopListAdapter()
        binding.recVievHolder.apply {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.NEED_TO_BUY, POOL_MAX_SIZE)
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.ALREADY_BOUGHT, POOL_MAX_SIZE)
        }
    }

    companion object{
        const val POOL_MAX_SIZE = 20
    }

}