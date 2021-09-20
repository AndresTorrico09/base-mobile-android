package com.example.myapplication.presentation.highlights

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.NetworkStatus
import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.presentation.recyclerView.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HighlightsViewModel constructor(
  private val productRepository: ProductRepository
) : ViewModel(), ProductAdapter.OnProductListener {

    private val _highlightList = MutableLiveData<Resource<List<Product>>>()
    val highligtList: LiveData<Resource<List<Product>>>
        get() = _highlightList

    private val _productSelected = MutableLiveData<Product>()
    val productSelected: LiveData<Product>
        get() = _productSelected

    fun searchHighlight(categoryName: String) {
        _highlightList.value = Resource(NetworkStatus.LOADING)
        viewModelScope.launch {
            _highlightList.value = withContext(Dispatchers.IO) {
                productRepository.getHighlightList(categoryName)
            }
        }
    }

    override fun onProductClicked(product: Product) {
        _productSelected.value = product
    }
}
