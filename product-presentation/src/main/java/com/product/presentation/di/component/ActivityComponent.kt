package com.product.presentation.di.component

import androidx.appcompat.app.AppCompatActivity
import com.product.presentation.di.PerActivity
import com.product.presentation.di.module.ActivityModule

import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): AppCompatActivity?
}