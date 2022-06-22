package com.example.shoppinglist.di

import android.app.Application
import com.example.shoppinglist.Presentation.MainActivity
import com.example.shoppinglist.Presentation.SingleItemScreenFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

@ApplicationScope
@Component(modules = [DataModule::class, VievModelModules::class])
interface ApplicationComponent {

    @Inject
    fun inject(activity: MainActivity)

    @Inject
    fun inject(fragment: SingleItemScreenFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}