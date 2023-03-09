package com.aguadaserra.app.main_ui.fragment.intro

import android.content.Intent
import android.os.Bundle
import android.text.TextPaint
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.main_ui.activity.MainActivity
import com.aguadaserra.app.main_ui.adapter.ViewPagerFragmentAdapter
import com.aguadaserra.app.util.Preferences
import kotlinx.android.synthetic.main.fragment_intro_type1.*
import kotlinx.android.synthetic.main.fragment_multiple_intro_container.*
import kotlinx.android.synthetic.main.fragment_sign_in.*

/**
 * A simple [Fragment] subclass.
 */
class MultipleIntroContainerFragment : BaseFragment() {


    private val fragmentList = ArrayList<Fragment>()
    private var viewPagerFragmentAdapter: ViewPagerFragmentAdapter? = null

    override var toolbarVisibility: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_intro_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful.createLink(signIn_tv,
            "Já possui uma conta? Entre aqui",
            "Entre aqui", object : ClickableSpan() {
                override fun onClick(widget: View) {
                    navigation.navigate(R.id.action_multipleIntroContainerFragment_to_signInFragment)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    // this is where you set link color, underline, typeface etc.
//                    val linkColor = ContextCompat.getColor(requireActivity(), R.color.colorPrimary)
//                    ds.color = linkColor
                    ds.isUnderlineText = true
                }
            })

        begin_bt.setOnClickListener {

//            if (checkBox.isChecked) {
//
                preferences.storeInt(Preferences.ENTERING_FIRST_TIME, 0)
//            } else {
//
//                preferences.storeInt(Preferences.ENTERING_FIRST_TIME, 2)
//            }

            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }

        fragmentList.add(
            IntroType1Fragment("A Água da Serra é uma marca historicamente jovem.", "", 0)
        )

        fragmentList.add(
            IntroType1Fragment("Agora com mais sabores além do original", "", 1)
        )

        fragmentList.add(
            IntroType1Fragment("Sabores para todos os gostos", "", 2)
        )



        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(childFragmentManager, lifecycle, fragmentList)
        viewPager.adapter = viewPagerFragmentAdapter

        indicator.setViewPager(viewPager)
    }

}