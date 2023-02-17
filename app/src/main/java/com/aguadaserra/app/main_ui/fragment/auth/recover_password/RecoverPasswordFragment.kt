package com.aguadaserra.app.main_ui.fragment.auth.recover_password

import android.os.Bundle
import android.text.Editable
import android.text.TextPaint
import android.text.TextWatcher
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aguadaserra.app.R
import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import com.aguadaserra.app.global_model.User
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.global_ui.dialog.GenericDialogFragment
import com.aguadaserra.app.util.KeyboardUtils
import kotlinx.android.synthetic.main.fragment_recover_password.*
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass.
 */
class RecoverPasswordFragment : BaseFragment() {

    private val viewModel: RecoverPasswordViewModel by viewModels()

    override var hasBackButton: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recover_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful.createLink(signUp_tv,
            "Já tem uma conta? Entre aqui",
            "Entre aqui", object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // your action

                    navigation.popBackStack()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    // this is where you set link color, underline, typeface etc.
                    val linkColor = ContextCompat.getColor(requireActivity(), R.color.colorPrimary)
                    ds.color = linkColor
                    ds.isUnderlineText = true
                }
            })

        next_bt.setOnClickListener {
            navigation.navigate(R.id.action_recoverPasswordFragment_to_verifyTokenFragment)
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer {

            next_bt.isProgressVisible = it.isLoading

        })

        viewModel.recoverResponseLiveData.observe(viewLifecycleOwner, ::onRecoverResponse)

    }

    override fun onStart() {
        super.onStart()

        viewModel.setupGenericErrorDisplay(requireActivity(), viewLifecycleOwner, parentFragmentManager)
    }

    private fun onRecoverResponse(response: ServiceResponse<List<User>>) {
        when (response) {
            is ServiceResponse.Success -> {

                val responseInfo = response.value!![0]

                responseInfo.msg?.let {
                    singleToast.show(requireActivity(),
                        it, Toast.LENGTH_LONG)
                }

                if (responseInfo.status.equals("01")) {

                    navigation.popBackStack()
                }

            }
            is ServiceResponse.Error.GenericError -> {
                //Verifica se é um codigo de erro conhecido
                val errorMsg = when(response.message) {
                    else -> getString(R.string.no_connection_description)
                }

                GenericDialogFragment.showErrorDialog(requireActivity(), getString(R.string.attention), errorMsg, parentFragmentManager)
            }
            is ServiceResponse.Error.NetworkError -> {
                GenericDialogFragment.showConnectionError(requireActivity(), ::sendEmailPressed, parentFragmentManager)
            }
        }
    }


    private fun sendEmailPressed(v: View?) {
        KeyboardUtils.hideKeyboard(requireActivity())
        viewModel.recoverPassword(
            email = email_et.text.toString())
    }
}