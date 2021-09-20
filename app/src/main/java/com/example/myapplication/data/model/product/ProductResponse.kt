package com.example.myapplication.data.model.product

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("body") val product: Product
)
