package com.example.shoppinglist.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //    private val vievModelMainActivity: VievModelMainActivity by viewModels()
    lateinit var vievModelMainActivity: VievModelMainActivity

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vievModelMainActivity = ViewModelProvider(this)[VievModelMainActivity::class.java]
        vievModelMainActivity.shoppingList.observe(this){
            Log.d("kkk", it.toString())
        }
        vievModelMainActivity.getList()
        vievModelMainActivity.deleteBuyItemFromList(BuyItem("buy 1", 1, true, 1))
        vievModelMainActivity.editItemEnableOrNot(BuyItem("buy 3", 3, true, 3))


    }
}