package com.aguadaserra.app.main_ui.fragment.intro

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aguadaserra.app.main_ui.activity.MainActivity
import com.aguadaserra.app.R
import com.aguadaserra.app.util.Preferences
import kotlinx.android.synthetic.main.fragment_intro_type1.*

/**
 * A simple [Fragment] subclass.
 */
class IntroType1Fragment(private val title: String, private val subTitle: String, private val position: Int) : Fragment() {

    private lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_type1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = Preferences(requireActivity())


        titleIntro_tv.text = title
        subTitleIntro_tv.text = subTitle

        var drawable: Drawable? = null
        var color: Int? = null

        when (position) {
            0 -> {
                drawable = resources.getDrawable(R.drawable.intro1_image)
            }
            1 -> {

                drawable = resources.getDrawable(R.drawable.intro2_image)
            }
            2 -> {

                drawable = resources.getDrawable(R.drawable.intro3_image)
            }

        }

        default_iv.setImageDrawable(drawable)

    }

}