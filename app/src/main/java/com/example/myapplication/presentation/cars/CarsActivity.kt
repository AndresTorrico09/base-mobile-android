package com.example.myapplication.presentation.cars

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.data.model.product.ProductCondition
import com.example.myapplication.data.model.product.ProductStatus
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
        updateRecyclerView(
            listOf(
                Product(
                    "1",
                    "title",
                    "20",
                    "thumb",
                    true,
                    ProductCondition.NEW,
                    ProductStatus.ACTIVE
                ),
                Product(
                    "2",
                    "title2",
                    "40",
                    "thumb",
                    true,
                    ProductCondition.NEW,
                    ProductStatus.ACTIVE
                )
            )
        )
    }

    private fun setObservers() {
        with(viewModel) {
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

    private fun updateRecyclerView(productList: List<Product>?) {
        with(products) {
            clear()
            addAll(productList ?: emptyList())
        }
        productAdapter.notifyDataSetChanged()
    }

    private fun navigateToProductDetail(product: Product) {
        DetailActivity.start(this, product)
    }
}