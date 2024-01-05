package com.example.sqlitedatabaseproductassignment.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqlitedatabaseproductassignment.database.dao.ProductDao
import com.example.sqlitedatabaseproductassignment.database.entity.Product

@Database(version = 1, entities = [Product::class])
abstract class EcomDatabase : RoomDatabase() {
    abstract  fun getProductDao() : ProductDao
    //abstract fun addProductDao() : ProductDao

    companion object{
        private var ecomDb: EcomDatabase? = null

        fun getInstance(context: Context):EcomDatabase{
            if (ecomDb == null){
                ecomDb = Room.databaseBuilder(
                    context,
                EcomDatabase::class.java,
                "ecomm_db"
                ).build()
            }
            return ecomDb!!
        }
    }
}