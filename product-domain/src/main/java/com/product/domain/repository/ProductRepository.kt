package com.product.domain.repository

import com.product.domain.model.Product
import com.product.domain.model.ProductDetail
import io.reactivex.Observable

interface ProductRepository {
    fun getProducts(): Observable<List<Product>>
    fun getProduct(productId: Int): Observable<ProductDetail>
}