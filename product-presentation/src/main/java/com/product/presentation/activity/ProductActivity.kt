package com.product.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.product.presentation.R
import com.product.presentation.fragment.FragmentUtil
import com.product.presentation.fragment.PaymentDialogFragment
import com.product.presentation.fragment.ProductDetailFragment
import com.product.presentation.fragment.ProductListFragment

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_activity)

        FragmentUtil.replaceFragment(
            this,
            R.id.product_list_container,
            ProductListFragment(),
            false
        )

        if (resources.getBoolean(R.bool.isTablet)) {
            FragmentUtil.replaceFragment(
                this, R.id.product_detail_container,
                ProductDetailFragment(-1), false
            )
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.credit_card -> {
                PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
                true
            }
            else -> false
        }
    }

}