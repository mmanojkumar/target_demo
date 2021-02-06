package com.product.presentation

import android.app.Application
import com.product.presentation.di.component.ApplicationComponent
import com.product.presentation.di.component.DaggerApplicationComponent
import com.product.presentation.di.module.ApplicationModule
import com.product.presentation.di.module.NetworkModule


class AndroidApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule()).build()
    }

}