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

    private var cachedProducts: Observable<List<Product>>? = null
    private var cachedProductDetails: HashMap<Int, Observable<ProductDetail>> = HashMap()


    override fun getProducts(): Observable<List<Product>>? {
        if (cachedProducts == null) {
            cachedProducts = restClient.retrofit.create(ProductApi::class.java).execute().map(
                ProductEntityDataMapper::toProduct
            )
        }
        return cachedProducts
    }

    override fun getProduct(productId: Int): Observable<ProductDetail>? {
        if (cachedProductDetails[productId] == null) {
            cachedProductDetails[productId] =
                restClient.retrofit.create(ProductApi::class.java).execute(productId).map(
                    ProductEntityDataMapper::toProductDetail
                )
        }
        return cachedProductDetails[productId]
    }

}