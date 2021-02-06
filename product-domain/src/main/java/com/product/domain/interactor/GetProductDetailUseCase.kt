package com.product.domain.interactor

import com.product.domain.BaseUseCase
import com.product.domain.model.ProductDetail
import com.product.domain.repository.ProductRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetProductDetailUseCase @Inject constructor(var productRepository: ProductRepository) :
    BaseUseCase<ProductDetail, GetProductDetailUseCase.Params>() {

    class Params constructor(val productId: Int)

    override fun buildUseCaseObservable(params: Params?): Observable<ProductDetail>? {
        return productRepository.getProduct(params!!.productId)
    }

}