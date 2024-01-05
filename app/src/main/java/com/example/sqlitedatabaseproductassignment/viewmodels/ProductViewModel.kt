package com.example.sqlitedatabaseproductassignment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sqlitedatabaseproductassignment.database.entity.Product
import com.example.sqlitedatabaseproductassignment.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel (
    private val productRepository : ProductRepository
): ViewModel() {

    val productsUpdatedAvailableLiveData = MutableLiveData<Boolean>()
    val product = ArrayList<Product>()

    fun fetchProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val products = productRepository.fetchProducts()

            withContext(Dispatchers.Main) {
                this@ProductViewModel.product.addAll(products)
                productsUpdatedAvailableLiveData.postValue(true)
            }
        }
    }

    val productPostedLiveData = MutableLiveData<Boolean>()

    fun addProduct(product: Product){
        CoroutineScope(Dispatchers.IO).launch {
            val products = productRepository.addProduct(product)

            withContext(Dispatchers.Main){
                productPostedLiveData.postValue(true)
            }
        }
    }
}