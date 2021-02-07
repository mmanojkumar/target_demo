package com.product.presentation.di.module

import android.app.Application
import android.content.Context
import com.product.data.executor.JobExecutor
import com.product.data.repository.ProductDataRepository
import com.product.domain.executor.PostExecutionThread
import com.product.domain.executor.ThreadExecutor
import com.product.domain.repository.ProductRepository
import com.product.presentation.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private var application: Application) {


    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDataRepository: ProductDataRepository): ProductRepository {
        return productDataRepository
    }

}