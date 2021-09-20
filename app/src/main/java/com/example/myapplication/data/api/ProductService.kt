package com.example.myapplication.data.api

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.model.highlight.HighlightList
import com.example.myapplication.data.model.product.ProductResponse
import com.example.myapplication.data.model.section.Category
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("sites/MLA/domain_discovery/search")
    suspend fun getCategoryId(
        @Query("limit") limit: Int = 1,
        @Query("q") categoryName: String
    ): List<Category>

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("/highlights/MLA/category/{section_id}")
    suspend fun getHighlightList(@Path("section_id") section: String): HighlightList

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("/items")
    suspend fun getProductList(@Query("ids") productIdList: String): List<ProductResponse>
}