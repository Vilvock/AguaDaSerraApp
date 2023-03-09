package com.aguadaserra.app.main_ui.fragment.menu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_filter.*
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass.
 */
class FilterFragment : BaseFragment() {

    override var hasBackButton: Boolean = true
    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = false

    override var title: String = "Filtro de ofertas"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rangeSlider.valueFrom = 0f
        rangeSlider.valueTo = 1000f

        var currency = " R$"

        val valueList = ArrayList<Float>()

        valueList.add(0f)
        valueList.add(1000f)


        rangeSlider.values = valueList

        rangeSlider.setLabelFormatter { value: Float ->
            String.format("%.2f $currency", value)
        }

        filter_bt.setOnClickListener {
            navigation.navigate(R.id.action_filterFragment_to_filterResultsFragment)
        }

    }

}