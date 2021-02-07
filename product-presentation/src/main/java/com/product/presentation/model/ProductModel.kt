package com.product.presentation.model

data class ProductModel(
    val id: Int,
    val title: String,
    val aisle: String,
    val price: String? = null,
    val imageUrl: String?
)