package com.product.presentation.fragment


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment



abstract class ProductBaseFragment : Fragment(){
    abstract fun injectDaggerDependencies()
    abstract fun initUIComponents()
    abstract fun initObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDaggerDependencies()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUIComponents()
        initObservers()
    }

}