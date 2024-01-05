package com.example.sqlitedatabaseproductassignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sqlitedatabaseproductassignment.database.entity.Product
import com.example.sqlitedatabaseproductassignment.viewmodels.ProductViewModel

@Dao
interface ProductDao {
    @Query("select * from products")
    fun getProducts() : List<Product>

    @Insert
    fun addProduct(product: Product)
}