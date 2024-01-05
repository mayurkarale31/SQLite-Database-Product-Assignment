package com.example.sqlitedatabaseproductassignment.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedatabaseproductassignment.R
import com.example.sqlitedatabaseproductassignment.adapters.ProductsAdapter
import com.example.sqlitedatabaseproductassignment.database.db.EcomDatabase
import com.example.sqlitedatabaseproductassignment.database.entity.Product
import com.example.sqlitedatabaseproductassignment.databinding.ActivityMainBinding
import com.example.sqlitedatabaseproductassignment.databinding.ProductFragmentBinding
import com.example.sqlitedatabaseproductassignment.factory.MyViewModelFactory
import com.example.sqlitedatabaseproductassignment.repository.ProductRepository
import com.example.sqlitedatabaseproductassignment.viewmodels.ProductViewModel

class ProductFragment : Fragment() {

    private lateinit var binding: ProductFragmentBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initAdapters()
        initObservers()
        initListeners()

        productViewModel.fetchProducts()


        return binding.root
    }

    private fun initListeners() {
        binding.recyclerProducts.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    }
                }
            })
        binding.btnAddProducts.setOnClickListener {
            addAddProductFragment()
        }
    }

    private fun addAddProductFragment() {
        val addProductFragment = AddProductFragment()

        addProductFragment.onAddProductListener =
            object : AddProductFragment.OnAddProductListener {
                override fun onAddProduct(product: Product) {
                    productViewModel.product.add(product)
                    productAdapter.notifyItemInserted(productViewModel.product.size-1 )
                }
            }

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, addProductFragment, null)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        productViewModel.productsUpdatedAvailableLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                productAdapter.notifyDataSetChanged()
                //productAdapter.notifyItemInserted(products.size-1)
            }
        }
    }

    private fun initAdapters() {
        productAdapter = ProductsAdapter(productViewModel.product)
        binding.recyclerProducts.adapter = productAdapter
    }

    private fun initViewModels() {
        productViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                ProductRepository(
                    EcomDatabase.getInstance(requireContext()).getProductDao()
                )
            )
        )[ProductViewModel::class.java]
    }

    private fun initViews() {
        binding.recyclerProducts.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}