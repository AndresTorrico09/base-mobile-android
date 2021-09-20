package com.example.myapplication.data.model.product

import androidx.annotation.StringRes
import com.example.myapplication.R
import com.google.gson.annotations.SerializedName

enum class ProductCondition(@StringRes val value: Int) {
    @SerializedName("new") NEW(R.string.condition_new),
    @SerializedName("used") USED(R.string.condition_used)
}
