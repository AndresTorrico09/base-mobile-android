package com.example.myapplication.presentation.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.NetworkStatus
import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.presentation.recyclerView.ProductAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CarsViewModel constructor(
    private val productRepository: ProductRepository
) : ViewModel(), ProductAdapter.OnProductListener {

    private val _carsList = MutableLiveData<Resource<List<Product>>>()
    val carsList: LiveData<Resource<List<Product>>>
        get() = _carsList

    private val _productSelected = MutableLiveData<Product>()
    val productSelected: LiveData<Product>
        get() = _productSelected

    fun getCarsList() {
        _carsList.value = Resource(NetworkStatus.LOADING)
        viewModelScope.launch {
            //TODO: remove fake delay
            delay(2000)
            _carsList.value = productRepository.getCarsList()
        }
    }

    override fun onProductClicked(product: Product) {
        _productSelected.value = product
    }
}