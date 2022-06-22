package com.example.shoppinglist.di

import android.app.Application

class ShopLIstAPP: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}