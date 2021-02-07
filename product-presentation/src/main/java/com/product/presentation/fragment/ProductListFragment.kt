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
import com.product.data.exception.ApiException
import com.product.data.exception.NoInternetException
import com.product.presentation.AndroidApplication
import com.product.presentation.R
import com.product.presentation.adapter.ProductListAdapter
import com.product.presentation.databinding.ProductListFragmentBinding
import com.product.presentation.di.component.DaggerProductComponent
import com.product.presentation.di.module.ActivityModule
import com.product.presentation.model.ProductModel
import javax.inject.Inject


class ProductListFragment : BaseFragment() {

    @Inject
    lateinit var productListViewModel: ProductListViewModel

    private lateinit var productListFragmentBinding: ProductListFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.product_list_fragment,
            container, false
        )
        productListFragmentBinding.productListViewModel = productListViewModel
        return productListFragmentBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListViewModel.loadProducts()
    }

    override fun initDaggerDependencies() {
        activity?.let {
            DaggerProductComponent.builder().applicationComponent(
                (activity?.application as AndroidApplication).applicationComponent
            ).activityModule(ActivityModule(it as AppCompatActivity)).build().inject(this)
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

    private fun loadProductDetail(position: Int) {

        val productListAdapter: ProductListAdapter =
            productListFragmentBinding.productRecyclerView.adapter
                    as ProductListAdapter

        when (isTablet()) {
            true -> {
                replaceFragment(
                    R.id.product_detail_container,
                    ProductDetailFragment(productListAdapter.getItem(position).id),
                    false
                )
            }
            else -> {
                replaceFragment(
                    R.id.product_list_container,
                    ProductDetailFragment(productListAdapter.getItem(position).id)
                )
            }
        }
    }

    override fun initObservers() {
        productListViewModel.success.observe(this, Observer {
            if (it.isNotEmpty()) {
                showProducts(it)
                if (resources.getBoolean(R.bool.isTablet)) {
                    loadProductDetail(0)
                }
            } else {
                hideProducts()
                showErrorMessage(getString(R.string.no_result_found))
            }
        })

        productListViewModel.failure.observe(this, Observer {
            when (it) {
                is NoInternetException -> {
                    showErrorMessage(getString(R.string.no_internet_connection))
                }
                is ApiException -> {
                    showErrorMessage(it.message)
                }
                else -> {
                    showErrorMessage(getString(R.string.generic_error_message))
                }
            }
        })

        productListViewModel.loading.observe(this, Observer {
            if (it) {
                hideErrorMessage()
                hideProducts()
                showShimmer()
            } else {
                hideShimmer()
            }
        })
    }

    override fun getTitle(): String {
        return getString(R.string.product)
    }

    private fun hideShimmer() {
        productListFragmentBinding.includeShimmer.shimmer.stopShimmer()
        productListFragmentBinding.includeShimmer.shimmer.visibility = View.GONE
    }

    private fun showShimmer() {
        productListFragmentBinding.includeShimmer.shimmer.visibility = View.VISIBLE
        productListFragmentBinding.includeShimmer.shimmer.startShimmer()
    }

    private fun showErrorMessage(errorMessage: String) {
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
        val productListAdapter: ProductListAdapter =
            productListFragmentBinding.productRecyclerView.adapter as ProductListAdapter
        productListAdapter.productModels = it
        productListAdapter.notifyDataSetChanged()
        productListFragmentBinding.productRecyclerView.visibility = View.VISIBLE
    }


}