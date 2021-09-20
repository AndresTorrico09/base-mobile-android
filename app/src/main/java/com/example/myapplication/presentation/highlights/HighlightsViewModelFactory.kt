package com.example.myapplication.presentation.highlights

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.repository.product.datasource.ProductLocalDataSource
import com.example.myapplication.data.repository.product.datasource.ProductRemoteDataSource
import com.example.myapplication.domain.repository.ProductRepository

class HighlightsViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val sharedPrefs = context.getSharedPreferences(context.getString(R.string.preferences_key), Context.MODE_PRIVATE)

        val repository = ProductRepository(
            productRemoteDataSource = ProductRemoteDataSource(),
            productLocalDataSource = ProductLocalDataSource(sharedPrefs)
        )
        return HighlightsViewModel(repository) as T
    }
}
