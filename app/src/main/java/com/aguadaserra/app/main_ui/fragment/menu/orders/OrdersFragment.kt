package com.aguadaserra.app.main_ui.fragment.menu.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.main_ui.adapter.AddressAdapter
import com.aguadaserra.app.main_ui.adapter.OrderAdapter
import com.aguadaserra.app.util.RecyclerItemListener
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_orders.*

/**
 * A simple [Fragment] subclass.
 */
class OrdersFragment : BaseFragment() {

    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = true

    override var title: String = "Histórico de pedidos"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val list = ArrayList<Any>()

        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())

        val adapter = OrderAdapter(requireActivity(), list, object : RecyclerItemListener {
            override fun onClickListenerItem(item: Any?) {
                super.onClickListenerItem(item)

                navigation.navigate(R.id.action_ordersFragment_to_orderDetailFragment)
            }
        })
        val layoutManagerRv: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        orders_rv.layoutManager = layoutManagerRv
        orders_rv.adapter = adapter
    }

}