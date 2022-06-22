package com.example.shoppinglist.Presentation

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.Presentation.SingleItenScreenActivity.Companion.createIntentForSingleItemAdd as createIntentForSingleItemAdd
import com.example.shoppinglist.Presentation.SingleItenScreenActivity.Companion.createIntentForSingleItemEdit as createIntentForSingleItemEdit

class MainActivity : AppCompatActivity() {

    private lateinit var vievModelMainActivity: VievModelMainActivity
    lateinit var shopListAdapter: ShopListAdapter

    lateinit var currentFrag: SingleItemScreenFragment

    lateinit var binding: ActivityMainBinding

    var isLandScape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vievModelMainActivity = ViewModelProvider(this)[VievModelMainActivity::class.java]

        chechIsLadscapeOrientation()

        if (isLandScape) {
            currentFrag = SingleItemScreenFragment.newInstanceAddItem()
            createFragmentAndCommitForLandScape()
        }
        createAdapter()
        createMethodSwipeAndDelete()
        binding.floatActionBotom.setOnClickListener {
            createIntentForSingleItemAdd(this).also { startActivity(it) }
        }

        vievModelMainActivity.shoppingList.observe(this) {
            shopListAdapter.submitList(it)
        }
    }

    private fun chechIsLadscapeOrientation() {
        binding.guidelineCenter?.let {
            Toast.makeText(this, "land", Toast.LENGTH_LONG).show()
            isLandScape = true
        }
    }

//    private fun addOnClickListenerToFAB() {
//        binding.floatActionBotom.setOnClickListener {
//            Toast.makeText(this, "pressed add", Toast.LENGTH_SHORT).show()
//            Intent(this, SingleItenScreenActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//    }

    private fun createMethodSwipeAndDelete() {
        val callBack = object :
            SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemSwiped = shopListAdapter.currentList[viewHolder.adapterPosition]
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
                if (isLandScape) {
                    currentFrag = SingleItemScreenFragment.newInstanceEditItem(it.id)
                    createFragmentAndCommitForLandScape()

                } else {
                    createIntentForSingleItemEdit(this@MainActivity, it.id)
                        .also { startActivity(it) }
                }
            }
        }
    }
    private fun createFragmentAndCommitForLandScape(){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragContainerOnMainAct, currentFrag)
            .addToBackStack(null)
            .commit()
    }
    companion object {
        const val POOL_MAX_SIZE = 20
    }
}