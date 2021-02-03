package com.product.presentation.fragment

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.product.presentation.AndroidApplication
import com.product.presentation.R
import com.product.presentation.databinding.ProductDetailFragmentBinding
import com.product.presentation.di.component.DaggerProductComponent
import com.product.presentation.di.module.ActivityModule
import javax.inject.Inject


class ProductDetailFragment() : Fragment(){

    @Inject
    lateinit var productDetailViewModel: ProductDetailViewModel
    private lateinit var productDetailFragmentBinding:ProductDetailFragmentBinding
    private  var productId:Int = -1

    constructor(productId:Int):this(){
        this.productId = productId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            DaggerProductComponent.builder().
            applicationComponent((activity?.application as AndroidApplication).
            applicationComponent).activityModule(ActivityModule(it as AppCompatActivity)).build().
            inject(this)
        }

        if(productId != -1) {
            productDetailViewModel.loadProductDetail(productId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.product_detail_fragment,
             container, false
        )
        return productDetailFragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productDetailFragmentBinding.retry.setOnClickListener { productDetailViewModel.loadProductDetail(productId) }

        productDetailViewModel.success.observe(this, Observer {
            if(it != null){
                productDetailFragmentBinding.errorContainer.visibility = View.GONE
                productDetailFragmentBinding.productDetailContainer.visibility = View.VISIBLE
                productDetailFragmentBinding.productDetailModel = it
                loadProductImage()
                productDetailFragmentBinding.regularPrice.paintFlags = productDetailFragmentBinding.regularPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


            }else{
                productDetailFragmentBinding.productDetailContainer.visibility = View.GONE
                productDetailFragmentBinding.errorMessage.text = getString(R.string.no_result_found)
                productDetailFragmentBinding.errorContainer.visibility = View.VISIBLE
            }
        })

        productDetailViewModel.failure.observe(this, Observer {
            productDetailFragmentBinding.productDetailContainer.visibility = View.GONE
            productDetailFragmentBinding.errorContainer.visibility = View.VISIBLE
            productDetailFragmentBinding.errorMessage.text = it

        })

        productDetailViewModel.loading.observe(this, Observer {
            if(it){
                productDetailFragmentBinding.errorContainer.visibility = View.GONE
                productDetailFragmentBinding.productDetailContainer.visibility = View.GONE
                productDetailFragmentBinding.includeShimmer.productDetailShimmer.visibility = View.VISIBLE
                productDetailFragmentBinding.includeShimmer.productDetailShimmer.startShimmer()

            }else{
                productDetailFragmentBinding.includeShimmer.productDetailShimmer.stopShimmer()
                productDetailFragmentBinding.includeShimmer.productDetailShimmer.visibility = View.GONE
            }
        })

    }

    private fun loadProductImage(){
        val circularProgressDrawable = CircularProgressDrawable(productDetailFragmentBinding.root.context)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.setColorSchemeColors(Color.RED)
        circularProgressDrawable.centerRadius = 50f
        circularProgressDrawable.start()

        Glide.with(productDetailFragmentBinding.root.context)
            .load(productDetailFragmentBinding.productDetailModel!!.url)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.error_load_image)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(productDetailFragmentBinding.productImage)

    }


    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.product_detail)
    }
}