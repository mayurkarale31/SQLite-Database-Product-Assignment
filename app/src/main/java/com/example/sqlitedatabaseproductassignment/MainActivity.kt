package com.example.sqlitedatabaseproductassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlitedatabaseproductassignment.database.db.EcomDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EcomDatabase.getInstance(this)
    }
}