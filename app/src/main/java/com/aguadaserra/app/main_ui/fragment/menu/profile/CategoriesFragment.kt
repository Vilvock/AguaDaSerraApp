package com.aguadaserra.app.main_ui.fragment.menu.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.main_ui.adapter.CategoryAdapter
import com.aguadaserra.app.main_ui.adapter.FavoriteAdapter
import com.aguadaserra.app.util.RecyclerItemListener
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : BaseFragment() {

    override var title: String = "Categorias"

    override var toolbarVisibility: Boolean = true
    override var hasBackButton: Boolean = true
    override var bottomNavigationVisibility: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<Any>()

        list.add(Any())
        list.add(Any())
        list.add(Any())

        val adapter = CategoryAdapter(requireActivity(), list, object : RecyclerItemListener {
            override fun onClickListenerItem(item: Any?) {
                super.onClickListenerItem(item)

//                navigation.navigate(R.id.action_categoriesFragment_to_productDetailFragment)
            }

        })
        val layoutManagerRv: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        categories_rv.layoutManager = layoutManagerRv
        categories_rv.adapter = adapter
    }
}