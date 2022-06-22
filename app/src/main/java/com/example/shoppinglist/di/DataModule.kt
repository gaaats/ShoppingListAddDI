package com.example.shoppinglist.di

import android.app.Application
import com.example.shoppinglist.Data.ShopListDAO
import com.example.shoppinglist.Data.ShopListDataBase
import com.example.shoppinglist.Data.ShopingListRepositoryImpl
import com.example.shoppinglist.Domain.repository.ShopingListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository_to_RepositoryImpl(impl: ShopingListRepositoryImpl): ShopingListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopingListDAO(
            application: Application
        ): ShopListDAO {
            return ShopListDataBase.getDataBase(application).getShopListDAO()
        }
    }
}