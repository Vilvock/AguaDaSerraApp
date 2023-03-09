package com.aguadaserra.app.main_ui.fragment.menu.home

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.global_model.Photo
import com.aguadaserra.app.global_ui.components.CircleRecyclerViewDecoration
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.main_ui.adapter.CarrouselItemAdapter
import com.aguadaserra.app.main_ui.adapter.ProductAdapter
import com.aguadaserra.app.main_ui.adapter.ProductGridAdapter
import com.aguadaserra.app.util.RecyclerItemListener
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = true

    override var title: String = "Home"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        loadCarrousel()

        val list = ArrayList<Any>()

        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())

        val adapter = ProductAdapter(requireActivity(), list, object : RecyclerItemListener {
            override fun onClickListenerItem(item: Any?) {
                super.onClickListenerItem(item)

                navigation.navigate(R.id.action_homeFragment_to_productDetailFragment)
            }

        })
        val layoutManagerRv: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        products_rv.layoutManager = layoutManagerRv
        products_rv.adapter = adapter

        val adapterGrid = ProductGridAdapter(requireActivity(), list, object : RecyclerItemListener {
            override fun onClickListenerItem(item: Any?) {
                super.onClickListenerItem(item)

                navigation.navigate(R.id.action_homeFragment_to_productDetailFragment)
            }

        })


        productsGrid_rv.layoutManager = GridLayoutManager(requireContext(), 2)
        productsGrid_rv.adapter = adapterGrid
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_notification_menu, menu)
//
//        val menuItem = menu.findItem(R.id.notification_nav)
//
//        val actionView = menuItem.actionView
//        textCartItemCount = actionView.findViewById(R.id.cart_badge) as TextView
//        val bellIcon = actionView.findViewById(R.id.bell_icon) as FrameLayout
//
//        bellIcon.setOnClickListener {
//
//            navigation.navigate(R.id.action_userProfileFragment_to_notificationsFragment)
//        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification_nav -> {
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadCarrousel() {

        val photoList = ArrayList<Int>()

        photoList.add(R.drawable.banner1_image)
        photoList.add(R.drawable.banner2_image)

        val adapter = CarrouselItemAdapter(photoList)


        carrousel_rv.layoutManager = (object : LinearLayoutManager(requireContext(), HORIZONTAL, false) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                // force height of viewHolder here, this will override layout_height from xml
                lp.width = (width / 1.34).toInt()
                return true
            }
        })

        carrousel_rv.adapter = adapter


        carrousel_rv.addItemDecoration(CircleRecyclerViewDecoration(requireActivity()))
    }
}