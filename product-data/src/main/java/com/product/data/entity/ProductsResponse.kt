package com.product.data.entity

import com.google.gson.annotations.SerializedName

data class ProductEntity(val id:Int) {

    var title: String? = null

    var aisle: String? = null

    var description: String? = null

    @SerializedName("image_url")
    var imageUrl: String? = null

    @SerializedName("regular_price")
    var regularPriceEntity: RegularPriceEntity? = null

    @SerializedName("sale_price")
    var salePriceEntity: SalePriceEntity? = null
}