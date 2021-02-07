package com.product.domain.model

data class ProductDetail(
    val id: Int,
    val title: String,
    val description: String,
    var salesPrice: String?,
    var regularprice: String?,
    var imageUrl: String?)


