package com.example.testappvk.data.network

import com.example.testappvk.domain.models.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProductByPage(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): Products
}