package com.example.sqlitedatabaseproductassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedatabaseproductassignment.R
import com.example.sqlitedatabaseproductassignment.database.entity.Product
import com.example.sqlitedatabaseproductassignment.databinding.ProductViewBinding

class ProductsAdapter(
     private val products : ArrayList<Product>
) : RecyclerView.Adapter<ProductsAdapter.UserViewHolder>() {

    inner class UserViewHolder(view : View): RecyclerView.ViewHolder(view){

        val productViewBinding : ProductViewBinding
        init {
            productViewBinding = ProductViewBinding.bind(view)
        }
    }

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_view, null)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val product = products[position]
        holder.productViewBinding.txtId.text = product.id.toString()
        holder.productViewBinding.txtTitle.text = product.title
        holder.productViewBinding.txtPrice.text = product.price.toString()
    }
}