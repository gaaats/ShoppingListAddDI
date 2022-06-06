package com.example.shoppinglist.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivitySingleItenScreenBinding
import java.lang.RuntimeException

class SingleItenScreenActivity : AppCompatActivity() {

    private val viewModelSingleItem: ViewModelSingleItem by viewModels { factoryyy() }
    lateinit var binding: ActivitySingleItenScreenBinding
    private var mode_current = ""
    private var item_current_id = BuyItem.DEFAULT_INDEX

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleItenScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        parcingIntentAndInitLocalVariables()
        createScreen()


    }

    private fun createScreen() {
        when (mode_current) {
            MODE_EDIT -> {
                Toast.makeText(this, "id: ${item_current_id}", Toast.LENGTH_LONG).show()
                this.title = getString(R.string.edit_item_screen_title)
                viewModelSingleItem.getItemOnActivitySingle(item_current_id)
                viewModelSingleItem.buyItemFromGet.observe(this) {
                    binding.textInputName.setText(it.name)
                    binding.textInputCount.setText(it.total.toString())
                }
                binding.btnSave.setOnClickListener {
                    val name = binding.textInputName.text.toString()
                    val count = binding.textInputCount.text.toString()
                    viewModelSingleItem.editItemOnActivitySingle(name, count)
                    viewModelSingleItem.canAppCloseScrnSingleItem.observe(this) {
                        finish()
                    }
                }

            }
            MODE_ADD -> {
                this.title = getString(R.string.add_item_screen_title)
                binding.btnSave.setOnClickListener {
                    val name = binding.textInputName.text.toString()
                    val count = binding.textInputCount.text.toString()
                    viewModelSingleItem.addItemOnActivitySingle(name, count)
                    viewModelSingleItem.canAppCloseScrnSingleItem.observe(this) {
                        finish()
                    }
                }
            }
        }
    }

    private fun parcingIntentAndInitLocalVariables() {
        if (!intent.hasExtra(MODE)) {
            throw RuntimeException("there is no mode inside")
        }
        if (!(intent.getStringExtra(MODE) == MODE_ADD || intent.getStringExtra(MODE) == MODE_EDIT)) {
            throw RuntimeException("there is no NO SUCH MODE")
        }
        mode_current = intent!!.getStringExtra(MODE).toString()
        item_current_id = intent.getIntExtra(ITEM_ID, BuyItem.DEFAULT_INDEX)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }

    companion object {
        private const val MODE = "MODE"
        private const val MODE_ADD = "MODE_ADD"
        private const val MODE_EDIT = "MODE_EDIT"
        private const val ITEM_ID = "ITEM_ID"


        fun createIntentForSingleItemAdd(context: Context): Intent {
            Intent(context, SingleItenScreenActivity::class.java).putExtra(MODE, MODE_ADD)
                .also { return it }
        }

        fun createIntentForSingleItemEdit(context: Context, id: Int): Intent {
            Intent(context, SingleItenScreenActivity::class.java).putExtra(MODE, MODE_EDIT)
                .putExtra(ITEM_ID, id)
                .also { return it }
        }
    }
}