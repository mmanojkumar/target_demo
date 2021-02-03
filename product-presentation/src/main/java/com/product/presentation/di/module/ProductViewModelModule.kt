package com.product.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


import com.giphy.di.factory.ViewModelKey
import com.product.presentation.di.factory.ProductViewModelFactory
import com.product.presentation.fragment.ProductListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ProductViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun bindUserViewModel(productListViewModel: ProductListViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ProductViewModelFactory): ViewModelProvider.Factory
}