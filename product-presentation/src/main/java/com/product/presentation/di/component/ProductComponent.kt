package com.product.presentation.di.component

import com.product.presentation.di.PerActivity
import com.product.presentation.di.module.ActivityModule
import com.product.presentation.di.module.ProductModule
import com.product.presentation.di.module.ProductViewModelModule
import com.product.presentation.fragment.ProductDetailFragment
import com.product.presentation.fragment.ProductListFragment

import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class,
        ProductModule::class,
        ProductViewModelModule::class]
)
interface ProductComponent : ActivityComponent {
    fun inject(productListFragment: ProductListFragment?)
    fun inject(productListFragment: ProductDetailFragment?)

    //fun inject(userDetailsFragment: ProductListFragment?)
}