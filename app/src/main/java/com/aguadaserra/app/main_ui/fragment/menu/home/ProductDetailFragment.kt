package com.aguadaserra.app.main_ui.fragment.menu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.global_model.Photo
import com.aguadaserra.app.global_ui.components.CircleRecyclerViewDecoration
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.main_ui.adapter.CarrouselItemAdapter
import com.aguadaserra.app.main_ui.adapter.CarrouselProductItemAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailFragment : BaseFragment() {

    override var hasBackButton: Boolean = true
    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = false

    override var title: String = "Detalhes da oferta"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadCarrousel()

    }




    private fun loadCarrousel() {



        val photoList = ArrayList<Int>()

        photoList.add(R.drawable.randon1)
        photoList.add(R.drawable.randon2)
        photoList.add(R.drawable.randon3)
        photoList.add(R.drawable.randon4)

        val adapter = CarrouselProductItemAdapter(photoList)


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