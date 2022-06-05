package com.example.shoppinglist.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivitySingleItenScreenBinding

class SingleItenScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySingleItenScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleItenScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        changeActivityToolBArTitle()
    }

    private fun changeActivityToolBArTitle() {
        this.title = if (intent.getStringExtra(MODE) == MODE_ADD) {
            getString(R.string.add_item_screen_title)
        } else {
            getString(R.string.edit_item_screen_title)
        }
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