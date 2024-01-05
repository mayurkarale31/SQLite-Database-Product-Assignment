package com.example.sqlitedatabaseproductassignment.repository

import com.example.sqlitedatabaseproductassignment.database.dao.ProductDao
import com.example.sqlitedatabaseproductassignment.database.entity.Product
import com.example.sqlitedatabaseproductassignment.viewmodels.ProductViewModel

class ProductRepository (
    private val productDao : ProductDao
){
    suspend fun fetchProducts():List<Product>{
        return productDao.getProducts()
    }

    suspend fun addProduct(
        product: Product
    ) {
        return productDao.addProduct(product)
    }
}