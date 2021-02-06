package com.product.presentation.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.product.domain.interactor.DefaultObserver
import com.product.domain.interactor.GetProductDetailUseCase
import com.product.domain.model.ProductDetail
import com.product.presentation.mapper.ProductModelMapper
import com.product.presentation.model.ProductDetailModel

import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(var productDetailUseCase: GetProductDetailUseCase) :
    ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val failure: MutableLiveData<Throwable> = MutableLiveData()
    val success: MutableLiveData<ProductDetailModel> = MutableLiveData()

    fun loadProductDetail(productId: Int) {
        loading.postValue(true)
        productDetailUseCase.execute(GetProductDetailUseCase.Params(productId),
            object : DefaultObserver<ProductDetail>() {
                override fun onNext(productDetail: ProductDetail) {
                    success.postValue(ProductModelMapper.toProductDetailModel(productDetail))
                }

                override fun onComplete() {
                    loading.postValue(false)
                }

                override fun onError(exception: Throwable?) {
                    loading.postValue(false)
                    failure.postValue(exception)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        productDetailUseCase.dispose()
    }

}