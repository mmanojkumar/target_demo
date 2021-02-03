package com.product.presentation.di.component


import android.content.Context
import com.product.data.network.RestClient
import com.product.presentation.di.module.ApplicationModule
import com.product.presentation.di.module.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface NetworkComponent{
    fun retrofit(): Retrofit
    fun restClient(): RestClient
    fun context() : Context
}