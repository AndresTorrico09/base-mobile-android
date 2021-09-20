package com.example.myapplication.data.repository.product.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductLocalDataSource constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveFavourite(productId: String) {
        val jsonString = sharedPreferences.getString(FAVOURITE_KEY, "")
        var productList = jsonString?.let {
            val type = object : TypeToken<List<String?>?>() {}.type
            Gson().fromJson(jsonString, type)
        } ?: run {
            listOf<String>()
        }
        if (!productList.contains(productId)) {
            productList += productId
            sharedPreferences.edit().putString(FAVOURITE_KEY, Gson().toJson(productList)).apply()
        }
    }

    companion object {
        private const val FAVOURITE_KEY = "favourite_key"
    }
}
