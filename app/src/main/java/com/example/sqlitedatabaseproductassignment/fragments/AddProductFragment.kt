package com.example.sqlitedatabaseproductassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sqlitedatabaseproductassignment.database.db.EcomDatabase
import com.example.sqlitedatabaseproductassignment.database.entity.Product
import com.example.sqlitedatabaseproductassignment.databinding.AddProductFragmentBinding
import com.example.sqlitedatabaseproductassignment.databinding.ProductFragmentBinding
import com.example.sqlitedatabaseproductassignment.factory.MyViewModelFactory
import com.example.sqlitedatabaseproductassignment.repository.ProductRepository
import com.example.sqlitedatabaseproductassignment.viewmodels.ProductViewModel

class AddProductFragment : Fragment() {

    private lateinit var addProductBinding : AddProductFragmentBinding

    private lateinit var productViewModel: ProductViewModel


    interface OnAddProductListener{
        fun onAddProduct(product: Product)
    }

    var onAddProductListener : OnAddProductListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addProductBinding = AddProductFragmentBinding.inflate(layoutInflater)

        initViewModels()
        initListeners()
        initObservers()

        return addProductBinding.root
    }
    private fun initObservers(){
        productViewModel.productPostedLiveData.observe(
            viewLifecycleOwner
        ){
            removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment(){
        parentFragmentManager.popBackStack()
    }

    private fun initListeners(){
        addProductBinding.btnAddProduct.setOnClickListener {
            val newProduct = Product(
                addProductBinding.edtId.text.toString().toInt(),
                addProductBinding.edtTitle.text.toString(),
                addProductBinding.edtPrice.text.toString().toInt()
            )
            productViewModel.addProduct( newProduct )

            if(onAddProductListener != null){
                onAddProductListener!!.onAddProduct(newProduct)
            }
            removeCurrentFragment()
        }
    }

    private fun initViewModels(){
        productViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                ProductRepository(
                    EcomDatabase.getInstance(requireContext()).getProductDao()
                )
            )
        )[ProductViewModel::class.java]
    }
}