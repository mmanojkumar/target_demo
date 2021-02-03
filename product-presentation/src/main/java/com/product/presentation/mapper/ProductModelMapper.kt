package com.product.presentation.mapper

import com.product.domain.model.Product
import com.product.domain.model.ProductDetail
import com.product.presentation.model.ProductDetailModel
import com.product.presentation.model.ProductModel

object ProductModelMapper {

    fun toProductModel(products: List<Product>?) : List<ProductModel>{
        val productModels = mutableListOf<ProductModel>()
        products?.forEach {
            productModels.add(ProductModel(it.id).apply {
                title  = it.title
                aisle = it.aisle
                url = it.url
                price = it.price
            })
        }

        return productModels
    }


    fun toProductDetailModel(productDetail: ProductDetail): ProductDetailModel {
        return ProductDetailModel(productDetail.id).apply {
            salesPrice = productDetail.salesPrice
            regularprice = productDetail.regularprice
            description = productDetail.description
            url = productDetail.url
            title = productDetail.title
        }
    }
}