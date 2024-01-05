package com.example.sqlitedatabaseproductassignment.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sqlitedatabaseproductassignment.repository.ProductRepository
import com.example.sqlitedatabaseproductassignment.viewmodels.ProductViewModel

class MyViewModelFactory(
    private val productRepository: ProductRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T
    }
}