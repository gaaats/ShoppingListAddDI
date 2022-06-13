package com.example.shoppinglist.Presentation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val result = when (modelClass) {
//            ViewModelSingleItem::class.java -> ViewModelSingleItem()
//            VievModelMainActivity::class.java -> VievModelMainActivity()
            else -> {throw IllegalArgumentException("there is no such class")}
        }
        return result as T

//        val result = if (modelClass.isAssignableFrom(ViewModelSingleItem::class.java)) {
//            ViewModelSingleItem()
//        } else {
//            VievModelMainActivity()
//        }
//        return result as T
    }
}

fun Activity.factoryyy() = ViewModelFactory()
fun Fragment.faaaactory() = ViewModelFactory()