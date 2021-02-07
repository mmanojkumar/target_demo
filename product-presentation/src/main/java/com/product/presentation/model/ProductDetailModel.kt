package com.product.presentation.model

data class ProductDetailModel(
    val id: Int,
    val title: String,
    val description: String,
    var salesPrice: String? = null,
    var regularprice: String? = null,
    val imageUrl: String?
)

