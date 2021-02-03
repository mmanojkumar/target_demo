package com.product.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.presentation.AndroidApplication
import com.product.presentation.R
import com.product.presentation.adapter.ProductListAdapter
import com.product.presentation.databinding.ProductListFragmentBinding
import com.product.presentation.di.component.DaggerProductComponent
import com.product.presentation.di.module.ActivityModule
import com.product.presentation.model.ProductModel
import javax.inject.Inject


class ProductListFragment : ProductBaseFragment(){

    @Inject
    lateinit var productListViewModel:ProductListViewModel

    private lateinit var productListFragmentBinding:ProductListFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListViewModel.loadProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.product_list_fragment,
            container, false
        )
        productListFragmentBinding.productListViewModel = productListViewModel
        return productListFragmentBinding.root

    }


    override fun injectDaggerDependencies() {
        activity?.let {
            DaggerProductComponent.builder().
            applicationComponent((activity?.application as AndroidApplication).
            applicationComponent).activityModule(ActivityModule(it as AppCompatActivity)).build().
            inject(this)
        }
    }

    override fun initUIComponents() {
        productListFragmentBinding.productRecyclerView.layoutManager =
            LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            productListFragmentBinding.productRecyclerView.context,
            (productListFragmentBinding.productRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        productListFragmentBinding.productRecyclerView.addItemDecoration(dividerItemDecoration)

        val productListAdapter = ProductListAdapter(mutableListOf())
        productListAdapter.onItemClickListener = object : ProductListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                loadProductDetail(position)
            }
        }
        productListFragmentBinding.productRecyclerView.adapter = productListAdapter

    }

    private fun loadProductDetail(
        position: Int) {
        val detailContainerId =   if(resources.getBoolean(R.bool.isTablet)) R.id.product_detail_container else R.id.product_list_container
        val productListAdapter:ProductListAdapter =  productListFragmentBinding.productRecyclerView.adapter as ProductListAdapter
        var fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()?.replace(
            detailContainerId,
            ProductDetailFragment(productListAdapter.getItem(position).id),
            "Detail"
        )
        if(resources.getBoolean(R.bool.isTablet)){
            fragmentTransaction?.commit()
        }else{
            fragmentTransaction?.addToBackStack(null)?.commit()
        }
    }

    override fun initObservers() {
        productListViewModel.success.observe(this, Observer {
            if(it.isNotEmpty()){
                showProducts(it)
                if(resources.getBoolean(R.bool.isTablet)) {
                    loadProductDetail(0)
                }
            }else{
                hideProducts()
                showErrorMessage(getString(R.string.no_result_found))
            }
        })

        productListViewModel.failure.observe(this, Observer {
            showErrorMessage(it)
        })

        productListViewModel.loading.observe(this, Observer {
            if(it){
                hideErrorMessage()
                hideProducts()
                showShimmer()
            }else{
                hideShimmer()
            }
        })
    }

    private fun hideShimmer() {
        productListFragmentBinding.includeShimmer.shimmer.stopShimmer()
        productListFragmentBinding.includeShimmer.shimmer.visibility = View.GONE
    }

    private fun showShimmer() {
        productListFragmentBinding.includeShimmer.shimmer.visibility = View.VISIBLE
        productListFragmentBinding.includeShimmer.shimmer.startShimmer()
    }

    private fun showErrorMessage(errorMessage:String) {
        productListFragmentBinding.errorMessage.text = errorMessage
        productListFragmentBinding.errorMessage.visibility = View.VISIBLE
        productListFragmentBinding.retry.visibility = View.VISIBLE
    }

    private fun hideErrorMessage() {
        productListFragmentBinding.errorMessage.visibility = View.GONE
        productListFragmentBinding.retry.visibility = View.GONE
    }

    private fun hideProducts() {
        productListFragmentBinding.productRecyclerView.visibility = View.GONE
    }


    private fun showProducts(it: List<ProductModel>) {
        val productListAdapter:ProductListAdapter =  productListFragmentBinding.productRecyclerView.adapter as ProductListAdapter
        productListAdapter.productModels = it
        productListAdapter.notifyDataSetChanged()
        productListFragmentBinding.productRecyclerView.visibility = View.VISIBLE
    }


    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.product)
    }
}