package com.product.presentation.di.component

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.product.domain.executor.PostExecutionThread
import com.product.domain.executor.ThreadExecutor
import com.product.domain.repository.ProductRepository
import com.product.presentation.di.module.ApplicationModule
import com.product.presentation.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: AppCompatActivity)
    fun context(): Context
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun productRepository(): ProductRepository
}