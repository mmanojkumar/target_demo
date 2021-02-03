package com.product.presentation.di.module

import androidx.appcompat.app.AppCompatActivity
import com.product.presentation.di.PerActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun activity(): AppCompatActivity {
        return activity
    }

}