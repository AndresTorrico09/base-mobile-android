package com.example.myapplication.presentation.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.presentation.recyclerView.ProductAdapter

class CarsViewModel constructor(
    private val productRepository: ProductRepository
) : ViewModel(), ProductAdapter.OnProductListener {

    private val _highlightList = MutableLiveData<Resource<List<Product>>>()
    val highligtList: LiveData<Resource<List<Product>>>
        get() = _highlightList

    private val _productSelected = MutableLiveData<Product>()
    val productSelected: LiveData<Product>
        get() = _productSelected

    override fun onProductClicked(product: Product) {
        _productSelected.value = product
    }
}