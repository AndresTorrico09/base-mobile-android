package com.example.myapplication.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.repository.ProductRepository

class DetailViewModel constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    fun saveFavourite(productId: String) {
        productRepository.saveFavourite(productId)
    }
}
