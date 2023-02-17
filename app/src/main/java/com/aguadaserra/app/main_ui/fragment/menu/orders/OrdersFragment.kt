package com.aguadaserra.app.main_ui.fragment.menu.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class OrdersFragment : BaseFragment() {

    override var toolbarVisibility: Boolean = false
    override var bottomNavigationVisibility: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

}