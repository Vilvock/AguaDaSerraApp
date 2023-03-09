package com.aguadaserra.app.main_ui.fragment.menu.cart.shop.pix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_method_payment.*
import kotlinx.android.synthetic.main.fragment_pix.*

/**
 * A simple [Fragment] subclass.
 */
class PixFragment : BaseFragment() {

    override var hasBackButton: Boolean = true
    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = false

    override var title: String = "PIX"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pix, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_bt.setOnClickListener {
            navigation.navigate(R.id.action_pixFragment_to_generatePixFragment)
        }
    }



}