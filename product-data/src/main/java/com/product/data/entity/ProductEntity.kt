package com.product.data.entity

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    val id: Int,
    val title: String,
    val description: String,
    val aisle: String
) {

    @SerializedName("image_url")
    var imageUrl: String? = null

    @SerializedName("regular_price")
    var regularPriceEntity: RegularPriceEntity? = null

    @SerializedName("sale_price")
    var salePriceEntity: SalePriceEntity? = null
}