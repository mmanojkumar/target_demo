package com.product.data.mapper

import com.product.data.entity.ProductEntity
import com.product.data.entity.ProductsResponse
import com.product.domain.model.Product
import com.product.domain.model.ProductDetail

object ProductEntityDataMapper{

    fun toProduct(productsResponse: ProductsResponse):List<Product>{
        val products = mutableListOf<Product>()
        productsResponse.productEntities?.forEach {
            products.add(Product(it.id).apply {
                title = it.title
                aisle = it.aisle
                url = it.imageUrl
                price = it.salePriceEntity?.displayString
            })
        }
        return products
    }


    fun toProductDetail(productEntity: ProductEntity):ProductDetail{
      return ProductDetail(productEntity.id).apply {
          salesPrice = productEntity.salePriceEntity?.displayString
          regularprice = productEntity.regularPriceEntity?.displayString
          description = productEntity.description
          url = productEntity.imageUrl
          title = productEntity.title
      }
    }
}