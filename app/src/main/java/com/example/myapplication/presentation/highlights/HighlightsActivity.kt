package com.example.myapplication.presentation.highlights

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.model.NetworkStatus
import com.example.myapplication.data.model.Resource
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.databinding.ActivityHighlightBinding
import com.example.myapplication.presentation.detail.DetailActivity
import com.example.myapplication.presentation.recyclerView.ProductAdapter

class HighlightsActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel: HighlightsViewModel by viewModels(
        factoryProducer = { HighlightsViewModelFactory(this) }
    )
    private lateinit var binding: ActivityHighlightBinding
    private lateinit var productAdapter : ProductAdapter
    private var products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighlightBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        setListener()
        initRecyclerView()
    }

    private fun setListener() {
        binding.svHighlights.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        productAdapter = ProductAdapter(products, viewModel)
        with(binding.highlightsList) {
            layoutManager = LinearLayoutManager(this@HighlightsActivity)
            adapter = productAdapter
        }
    }

    private fun setObservers() {
        with(viewModel) {
            highligtList.observe(this@HighlightsActivity, Observer(::handleHighlightList))
            productSelected.observe(this@HighlightsActivity, Observer(::navigateToProductDetail))
        }
    }

    private fun handleHighlightList(highlightListResource: Resource<List<Product>>) {
        with(binding) {
            when (highlightListResource.status) {
                NetworkStatus.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    highlightsList.visibility = View.VISIBLE
                    updateRecyclerView(highlightListResource.data)
                }
                NetworkStatus.ERROR -> {
                    progressBar.visibility = View.GONE
                    highlightsList.visibility = View.GONE
                }
                NetworkStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun updateRecyclerView(productList: List<Product>?) {
        with(products) {
            clear()
            addAll(productList ?: emptyList())
        }
        productAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) viewModel.searchHighlight(query.lowercase())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun navigateToProductDetail(product: Product) {
        DetailActivity.start(this, product)
    }
}
