package com.example.myapplication.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.model.product.Product
import com.example.myapplication.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var selectedProduct: Product
    private val viewModel: DetailViewModel by viewModels(
        factoryProducer = { DetailViewModelFactory(this) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedProduct = intent.extras?.get(PRODUCT_KEY) as Product
        renderProduct()

        setListeners()
    }

    private fun renderProduct() {
        with(binding) {
            Picasso.get().load(selectedProduct.thumbnail).into(thumbnail)
            title.text = selectedProduct.title
            price.text = String.format(getString(R.string.price_format), selectedProduct.price)
            condition.text = String.format(
                getString(R.string.condition_format),
                getString(selectedProduct.condition.value)
            )
            status.text = String.format(
                getString(R.string.status_format),
                getString(selectedProduct.status.value)
            )
            
            acceptsMercadoPago.text = String.format(
                getString(R.string.accepts_mercadopago_format),
                if (selectedProduct.acceptsMercadoPago) getString(R.string.yes) else getString(R.string.no))
        }
    }

    private fun setListeners() {
        binding.favouriteButton.setOnClickListener {
            viewModel.saveFavourite(selectedProduct.id)
        }
    }

    companion object {
        fun start(context: Context, product: Product) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(PRODUCT_KEY, product)
            }
            context.startActivity(intent)
        }

        private const val PRODUCT_KEY = "product_key"
    }
}
