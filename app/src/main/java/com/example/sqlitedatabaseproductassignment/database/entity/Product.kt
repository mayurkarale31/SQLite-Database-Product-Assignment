package com.example.sqlitedatabaseproductassignment.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id : Int,
    val title : String,
    val price : Int = 0
)