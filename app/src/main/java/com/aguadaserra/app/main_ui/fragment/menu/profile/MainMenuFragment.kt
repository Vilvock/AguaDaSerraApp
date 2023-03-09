package com.aguadaserra.app.main_ui.fragment.menu.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.global_ui.dialog.GenericDialogFragment
import com.aguadaserra.app.main_ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_main_menu.*


/**
 * A simple [Fragment] subclass.
 */
class MainMenuFragment : BaseFragment() {

    override var toolbarVisibility: Boolean = true
    override var bottomNavigationVisibility: Boolean = true

    override var title: String = "Menu principal"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userData_ib.setOnClickListener {
            navigation.navigate(R.id.action_mainMenuFragment_to_userDataFragment)
        }


        address_ll.setOnClickListener {

            navigation.navigate(R.id.action_mainMenuFragment_to_userAddressFragment)
        }


        categories_ll.setOnClickListener {

            navigation.navigate(R.id.action_mainMenuFragment_to_categoriesFragment)
        }

        logout_ll.setOnClickListener {

            GenericDialogFragment.getInstance(
                getString(R.string.attention),
                getString(R.string.logout),
                getString(R.string.yes),
                getString(R.string.no)
            )
                .setOnRightOptionPressed {
                    preferences.clearUserData()
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    requireActivity().finishAffinity()
                }
                .setOnLeftOptionPressed { }
                .apply { isCancelable = false }
                .show(parentFragmentManager, "dialog_logout")
        }
    }

}