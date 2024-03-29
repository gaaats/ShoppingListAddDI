package com.example.shoppinglist.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.shoppinglist.Domain.model.BuyItem
import com.example.shoppinglist.Presentation.Constance.Constance
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivitySingleItenScreenBinding
import java.lang.RuntimeException

class SingleItenScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySingleItenScreenBinding
    private var mode_current = ""
    private var item_current_id = BuyItem.DEFAULT_INDEX

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("keyy", "OnCreate")
        super.onCreate(savedInstanceState)
        binding = ActivitySingleItenScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        parcingIntentAndInitLocalVariables()

        if (savedInstanceState == null){
            createFragment()
        }
    }

    private fun createFragment() {
        when (mode_current) {
            Constance.MODE_ADD -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, SingleItemScreenFragment.newInstanceAddItem())
                    .commit()
            }
            Constance.MODE_EDIT -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                        SingleItemScreenFragment.newInstanceEditItem(item_current_id)
                    ).commit()
            }
            else -> {
                throw RuntimeException("There is no such MODE, I can not create FRAGMENT")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }

    private fun parcingIntentAndInitLocalVariables() {
        if (!intent.hasExtra(Constance.MODE)) {
            throw RuntimeException("there is no mode inside")
        }
        if (!(intent.getStringExtra(Constance.MODE) == Constance.MODE_ADD || intent.getStringExtra(
                Constance.MODE
            ) == Constance.MODE_EDIT)
        ) {
            throw RuntimeException("there is no NO SUCH MODE")
        }
        mode_current = intent!!.getStringExtra(Constance.MODE).toString()
        item_current_id = intent.getIntExtra(Constance.ITEM_ID, BuyItem.DEFAULT_INDEX)
    }

    companion object {
        fun createIntentForSingleItemAdd(context: Context): Intent {
            Intent(context, SingleItenScreenActivity::class.java).putExtra(
                Constance.MODE,
                Constance.MODE_ADD
            )
                .also { return it }
        }

        fun createIntentForSingleItemEdit(context: Context, id: Int): Intent {
            Intent(context, SingleItenScreenActivity::class.java).putExtra(
                Constance.MODE,
                Constance.MODE_EDIT
            )
                .putExtra(Constance.ITEM_ID, id)
                .also { return it }
        }
    }
}