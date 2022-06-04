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
//    lateinit var vievModelMainActivity: VievModelMainActivity

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        vievModelMainActivity = ViewModelProvider(this)[VievModelMainActivity::class.java]
        vievModelMainActivity.shoppingList.observe(this) {
            shovListOfItems(it)
        }
    }

    private fun shovListOfItems(list: List<BuyItem>) {
        binding.linLayInMain.removeAllViews()
        for (element in list) {
            val typeOfLayout = if (element.isBuyed) {
                R.layout.item_to_buy_not_active
            } else {
                R.layout.item_to_buy_active
            }
            var view = LayoutInflater.from(this).inflate(typeOfLayout, binding.linLayInMain, false)
            view.findViewById<TextView>(R.id.tvNameOfItem).text = element.name
            view.findViewById<TextView>(R.id.tvSumOfItem).text = element.total.toString()
            view.setOnLongClickListener {
                vievModelMainActivity.editItemEnableOrNot(element)
                true
            }
            binding.linLayInMain.addView(view)
        }
    }
}