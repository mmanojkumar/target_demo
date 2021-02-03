package com.product.data.repository




import com.product.data.api.ProductApi
import com.product.data.mapper.ProductEntityDataMapper
import com.product.data.network.RestClient
import com.product.domain.model.Product
import com.product.domain.model.ProductDetail
import com.product.domain.repository.ProductRepository
import io.reactivex.Observable
import javax.inject.Inject

import javax.inject.Singleton

@Singleton
class ProductDataRepository @Inject constructor(var restClient: RestClient) : ProductRepository {

    override fun getProducts(): Observable<List<Product>> {
        return restClient.retrofit.create(ProductApi::class.java).execute().map(
            ProductEntityDataMapper::toProduct)
    }

    override fun getProduct(productId: Int): Observable<ProductDetail> {
        return restClient.retrofit.create(ProductApi::class.java).execute(productId).map(
            ProductEntityDataMapper::toProductDetail)
    }

}