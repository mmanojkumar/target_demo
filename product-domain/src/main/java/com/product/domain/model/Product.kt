package com.product.domain.model

data class Product(val id: Int) {

    var title: String? = null

    var aisle: String? = null

    var url: String? = null

    var price: String? = null
}


data class ProductDetailModel(val id: Int) {

    var title: String? = null

    var salesPrice: String? = null

    var regularprice: String? = null

    var url: String? = null

    var description: String? = null
}