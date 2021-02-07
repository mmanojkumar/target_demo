package com.product.data.mapper

import com.product.data.entity.ProductEntity
import com.product.data.entity.ProductsResponse
import com.product.domain.model.Product
import com.product.domain.model.ProductDetail

object ProductEntityDataMapper {

    fun toProduct(productsResponse: ProductsResponse): List<Product> {
        val products = mutableListOf<Product>()
        productsResponse.productEntities?.forEach {
            products.add(
                Product(
                    it.id,
                    it.title,
                    it.aisle,
                    it.salePriceEntity?.displayString,
                    it.imageUrl
                )
            )
        }
        return products
    }


    fun toProductDetail(productEntity: ProductEntity): ProductDetail {
        return ProductDetail(
            productEntity.id,
            productEntity.title,
            if (productEntity.description.length > 50000) {
                productEntity.description.substring(0, 50000)
            } else {
                productEntity.description
            },
            productEntity.salePriceEntity?.displayString,
            productEntity.regularPriceEntity?.displayString,
            productEntity.imageUrl
        )
    }

}