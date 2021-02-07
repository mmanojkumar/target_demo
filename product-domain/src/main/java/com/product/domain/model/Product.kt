package com.product.domain.model

data class Product(
    val id: Int,
    val title: String,
    val aisle: String,
    val price: String? = null,
    val imageUrl: String?
)
