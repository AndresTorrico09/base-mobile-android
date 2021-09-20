package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.data.repository.product.datasource.ProductLocalDataSource
import com.example.myapplication.data.repository.product.datasource.ProductRemoteDataSource

class ProductRepository constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) {

    suspend fun getHighlightList(categoryName: String): Resource<List<Product>> {
        return productRemoteDataSource.getHighlightList(categoryName)
    }

    fun saveFavourite(productId: String) {
        productLocalDataSource.saveFavourite(productId)
    }
}
