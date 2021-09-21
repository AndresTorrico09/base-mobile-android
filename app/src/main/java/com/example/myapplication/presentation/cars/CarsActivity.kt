package com.example.myapplication.presentation.cars

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.model.NetworkStatus
import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.databinding.ActivityCarsBinding
import com.example.myapplication.presentation.detail.DetailActivity
import com.example.myapplication.presentation.recyclerView.ProductAdapter

class CarsActivity : AppCompatActivity() {
    private val viewModel: CarsViewModel by viewModels(
        factoryProducer = { CarsViewModelFactory(this) }
    )
    private lateinit var binding: ActivityCarsBinding
    private lateinit var productAdapter: ProductAdapter
    private var products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        setObservers()

        viewModel.getCarsList()
    }

    private fun setObservers() {
        with(viewModel) {
            carsList.observe(this@CarsActivity, Observer(::updateCarsList))
            productSelected.observe(this@CarsActivity, Observer(::navigateToProductDetail))
        }
    }

    private fun initRecyclerView() {
        productAdapter = ProductAdapter(products, viewModel)
        with(binding.rvCars) {
            layoutManager = LinearLayoutManager(this@CarsActivity)
            adapter = productAdapter
        }
    }

    private fun updateCarsList(productList: Resource<List<Product>>) {
        with(products) {
            when (productList.status) {
                NetworkStatus.SUCCESS -> {
                    clear()
                    addAll(productList.data ?: emptyList())
                    productAdapter.notifyDataSetChanged()
                }
                NetworkStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvCars.visibility = View.GONE
                }
                NetworkStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun navigateToProductDetail(product: Product) {
        DetailActivity.start(this, product)
    }
}