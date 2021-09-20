package com.example.myapplication.data.repository.product.datasource

import com.example.myapplication.data.api.ProductService
import com.example.myapplication.data.model.NetworkStatus
import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.presentation.di.core.RetrofitServices

class ProductRemoteDataSource {

    private val service = RetrofitServices.instance.create(ProductService::class.java)

    suspend fun getHighlightList(categoryName: String): Resource<List<Product>> {
        return try {
            val response = service.getCategoryId(categoryName = categoryName)
            getSectionHighlightList(response.first().categoryId)
        } catch (t: Throwable) {
            Resource(NetworkStatus.ERROR, message = t.message)
        }
    }

    private suspend fun getSectionHighlightList(categoryId: String): Resource<List<Product>> {
        return try {
            val response = service.getHighlightList(categoryId)
            getProductList(response.idList)
        } catch (t: Throwable) {
            Resource(NetworkStatus.ERROR, message = t.message)
        }
    }

    private suspend fun getProductList(productIdList: String): Resource<List<Product>> {
        return try {
            val response = service.getProductList(productIdList)
            val responseMap = response.map { it.product }.filter { it.price != null }
            Resource(NetworkStatus.SUCCESS, responseMap)
        } catch (t: Throwable) {
            Resource(NetworkStatus.ERROR, message = t.message)
        }
    }
}
