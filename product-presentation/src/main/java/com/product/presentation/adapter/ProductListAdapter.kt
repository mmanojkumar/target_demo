package com.product.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.product.presentation.R
import com.product.presentation.databinding.ProductListRowBinding
import com.product.presentation.model.ProductModel


class ProductListAdapter(var productModels: List<ProductModel>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    class ProductViewHolder(val productListRowBinding: ProductListRowBinding) :
        RecyclerView.ViewHolder(productListRowBinding.root) {

        fun loadProductImage() {
            val circularProgressDrawable =
                CircularProgressDrawable(productListRowBinding.root.context)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.setColorSchemeColors(Color.RED)
            circularProgressDrawable.centerRadius = 50f
            circularProgressDrawable.start()


            Glide.with(productListRowBinding.root.context)
                .load(productListRowBinding.productModel!!.url)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.error_load_image)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(productListRowBinding.productImage)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductListRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return productModels.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productListRowBinding.productModel = getItem(position)
        holder.loadProductImage()
        holder.productListRowBinding.root.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    fun getItem(index: Int): ProductModel {
        return productModels[index]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}