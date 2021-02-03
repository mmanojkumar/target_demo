package com.product.presentation.di.module

import android.content.Context
import com.bumptech.glide.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.product.data.network.RestClient
import com.product.data.network.interceptor.InternetConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule{

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, url:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun restClient(retrofit: Retrofit): RestClient {
        return RestClient(
            retrofit
        )
    }

    @Provides
    fun okHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if(BuildConfig.DEBUG){
           builder.addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
        }
        builder.addInterceptor(InternetConnectionInterceptor(context))
        return builder.build()
    }


    @Provides
    fun url(): String {
        return "https://api.target.com"
    }


}