package com.product.data.entity

import com.google.gson.annotations.SerializedName


class ProductsResponse{
    @SerializedName("products")
    var productEntities: List<ProductEntity>? = null

}