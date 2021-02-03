package com.product.data.api

import com.product.data.entity.ProductEntity
import com.product.data.entity.ProductsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/mobile_case_study_deals/v1/deals")
    fun  execute(): Observable<ProductsResponse>

    @GET("/mobile_case_study_deals/v1/deals/{productId}")
    fun  execute(@Path("productId")id:Int): Observable<ProductEntity>


}