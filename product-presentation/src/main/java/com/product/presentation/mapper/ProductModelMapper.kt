package com.product.presentation.mapper

import com.product.domain.model.Product
import com.product.domain.model.ProductDetail
import com.product.presentation.model.ProductDetailModel
import com.product.presentation.model.ProductModel

object ProductModelMapper {

    fun toProductModel(products: List<Product>?): List<ProductModel> {
        val productModels = mutableListOf<ProductModel>()
        products?.forEach {
            productModels.add(ProductModel(it.id, it.title, it.aisle, it.price, it.imageUrl))
        }

        return productModels
    }


    fun toProductDetailModel(productDetail: ProductDetail): ProductDetailModel {
        return ProductDetailModel(
            productDetail.id,
            productDetail.title, productDetail.description,
            productDetail.salesPrice,
            productDetail.regularprice,
            productDetail.imageUrl
        )
    }
}