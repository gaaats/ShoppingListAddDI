package com.example.shoppinglist.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class VievModelKeyAnnotation(val value: KClass<out ViewModel>)
