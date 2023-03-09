package com.aguadaserra.app.main_ui.fragment.menu.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_method_payment.*

/**
 * A simple [Fragment] subclass.
 */
class MethodPaymentFragment : BaseFragment() {

    override var hasBackButton: Boolean = true
    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = false


    override var title: String = "Métodos de pagamento e entrega"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_method_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        address_item.setOnClickListener {
            navigation.navigate(R.id.action_methodPaymentFragment_to_userAddressFragment)
        }

        pix.setOnClickListener {
            navigation.navigate(R.id.action_methodPaymentFragment_to_pixFragment) }


        ticket.setOnClickListener {
            navigation.navigate(R.id.action_methodPaymentFragment_to_ticketFragment) }


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab!!.position == 0) {
                    schedule_ll.visibility = View.GONE
                } else {

                    schedule_ll.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

}