package com.aguadaserra.app.main_ui.fragment.auth.register

import android.os.Bundle
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.aguadaserra.app.R
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.main_ui.fragment.auth.register.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.signUp_tv
import kotlinx.android.synthetic.main.fragment_sign_up.*


/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : BaseFragment() {

    private val viewModel: SignUpViewModel by viewModels()

    override var hasBackButton: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_bt.setOnClickListener {
            navigation = requireActivity().findNavController(R.id.nav_host_fragment)

            val graph = navigation.navInflater.inflate(R.navigation.main_graph)
            graph.startDestination = R.id.homeFragment
            navigation.graph = graph
        }

        useful.createLink(signUp_tv,
            "Já possui uma conta? Entre aqui",
            "Entre aqui", object : ClickableSpan() {
                override fun onClick(widget: View) {
                    navigation.navigate(R.id.action_signInFragment_to_signUpFragment)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    // this is where you set link color, underline, typeface etc.
                    val linkColor = ContextCompat.getColor(requireActivity(), R.color.colorPrimary)
                    ds.color = linkColor
                    ds.isUnderlineText = true
                }
            })
    }

}