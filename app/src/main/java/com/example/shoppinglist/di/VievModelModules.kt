package com.example.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Presentation.VievModelMainActivity
import com.example.shoppinglist.Presentation.ViewModelSingleItem
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface VievModelModules {

    @Binds
    @IntoMap
    @VievModelKeyAnnotation(ViewModelSingleItem::class)
    fun bindViewModelSingleItem(impl: ViewModelSingleItem):ViewModel

    @Binds
    @IntoMap
    @VievModelKeyAnnotation(VievModelMainActivity::class)
    fun bindVievModelMainActivity(impl: VievModelMainActivity):ViewModel
}