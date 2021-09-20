package com.example.myapplication.data.model.product

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("accepts_mercadopago") val acceptsMercadoPago: Boolean,
    @SerializedName("condition") val condition: ProductCondition,
    @SerializedName("status") val status: ProductStatus
): Serializable
