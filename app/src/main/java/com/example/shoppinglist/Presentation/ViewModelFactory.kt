package com.example.shoppinglist.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val result = when (modelClass) {
//            ViewModelSingleItem::class.java -> ViewModelSingleItem()
//            VievModelMainActivity::class.java -> VievModelMainActivity()
            else -> {throw IllegalArgumentException("there is no such class")}
        }
        return result as T

    }
}

//fun Activity.factoryyy() = ViewModelFactory()
//fun Fragment.faaaactory() = ViewModelFactory()