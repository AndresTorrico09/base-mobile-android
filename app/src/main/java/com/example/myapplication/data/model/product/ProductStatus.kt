package com.example.myapplication.data.model.product

import androidx.annotation.StringRes
import com.example.myapplication.R
import com.google.gson.annotations.SerializedName

enum class ProductStatus(@StringRes val value: Int) {
    @SerializedName("active") ACTIVE(R.string.status_active),
    @SerializedName("inactive") INACTIVE(R.string.status_inactive)
}
