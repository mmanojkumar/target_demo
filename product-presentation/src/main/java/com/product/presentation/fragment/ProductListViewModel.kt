package com.product.presentation.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.product.domain.interactor.DefaultObserver
import com.product.domain.interactor.GetProductsUseCase
import com.product.domain.model.Product
import com.product.presentation.mapper.ProductModelMapper
import com.product.presentation.model.ProductModel
import javax.inject.Inject

class ProductListViewModel @Inject constructor(var productsUseCase:GetProductsUseCase) : ViewModel(){

    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val failure : MutableLiveData<String> = MutableLiveData()
    val success : MutableLiveData<List<ProductModel>> = MutableLiveData()

    fun loadProducts(){
        loading.postValue(true)
        productsUseCase.execute(null, object: DefaultObserver<List<Product>>() {
            override fun onNext(productList: List<Product>?) {
                success.postValue(ProductModelMapper.toProductModel(productList))
            }

            override fun onComplete() {
                loading.postValue(false)
            }

            override fun onError(exception: Throwable?) {
                loading.postValue(false)
                failure.postValue(exception?.message)
            }
        })
    }


}