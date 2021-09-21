package com.example.myapplication.data.repository.product.datasource

import android.content.SharedPreferences
import com.example.myapplication.data.model.NetworkStatus
import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.data.model.product.ProductCondition
import com.example.myapplication.data.model.product.ProductStatus
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

    fun getCarsList(): Resource<List<Product>> {
        return try {
            val response = listOf(
                Product(
                    "1",
                    "Volkswagen",
                    "10.000",
                    "thumb",
                    true,
                    ProductCondition.NEW,
                    ProductStatus.ACTIVE
                ),
                Product(
                    "2",
                    "Mercedes Benz",
                    "40.200",
                    "thumb",
                    false,
                    ProductCondition.NEW,
                    ProductStatus.ACTIVE
                ),
                Product(
                    "3",
                    "BMW",
                    "35.500",
                    "thumb",
                    true,
                    ProductCondition.USED,
                    ProductStatus.ACTIVE
                ),
                Product(
                    "4",
                    "Audi",
                    "28.900",
                    "thumb",
                    false,
                    ProductCondition.USED,
                    ProductStatus.ACTIVE
                ),
                Product(
                    "5",
                    "Fiat",
                    "8.500",
                    "thumb",
                    false,
                    ProductCondition.NEW,
                    ProductStatus.ACTIVE
                )
            )
            Resource(NetworkStatus.SUCCESS, response)
        } catch (t: Throwable) {
            Resource(NetworkStatus.ERROR, message = t.message)
        }

    }

    companion object {
        private const val FAVOURITE_KEY = "favourite_key"
    }
}
