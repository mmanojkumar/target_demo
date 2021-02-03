package com.product.domain.interactor

import com.product.domain.BaseUseCase
import com.product.domain.model.Product
import com.product.domain.repository.ProductRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetProductsUseCase  @Inject constructor (var productRepository:ProductRepository) : BaseUseCase<List<Product>, Void>() {

    override fun buildUseCaseObservable(params: Void?): Observable<List<Product>> {
        return productRepository.getProducts()
    }

}