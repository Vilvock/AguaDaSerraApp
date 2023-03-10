package com.aguadaserra.app.main_ui.fragment.menu.profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.global_ui.dialog.CustomDialogMessages
import com.aguadaserra.app.main_ui.adapter.AddressAdapter
import com.aguadaserra.app.main_ui.adapter.ProductAdapter
import com.aguadaserra.app.util.RecyclerItemListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user_address.*

/**
 * A simple [Fragment] subclass.
 */
class UserAddressFragment : BaseFragment() {


    override var hasBackButton: Boolean = true
    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = false


    override var title: String = "Endereços salvos"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)


        val list = ArrayList<Any>()

        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())
        list.add(Any())

        val adapter = AddressAdapter(requireActivity(), list, object : RecyclerItemListener {
            override fun onClickListenerItem(item: Any?) {
                super.onClickListenerItem(item)

            }

        })
        val layoutManagerRv: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        addresses_rv.layoutManager = layoutManagerRv
        addresses_rv.adapter = adapter

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_add_menu, menu)
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
            R.id.address_nav -> {
                customDialogMessages.openFormAddress(object : CustomDialogMessages.Answer {
                    override fun setOnClickListener() {

                    }

                })
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}