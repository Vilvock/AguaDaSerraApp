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
import androidx.navigation.findNavController
import com.aguadaserra.app.R
import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import com.aguadaserra.app.global_model.User
import com.aguadaserra.app.global_ui.config_fragment.BaseFragment
import com.aguadaserra.app.global_ui.dialog.GenericDialogFragment
import com.aguadaserra.app.main_ui.fragment.auth.recover_password.RecoverPasswordViewModel
import com.aguadaserra.app.util.KeyboardUtils
import kotlinx.android.synthetic.main.fragment_recover_password.*
import kotlinx.android.synthetic.main.fragment_verify_token.*
import kotlinx.android.synthetic.main.fragment_verify_token.next_bt
import kotlinx.android.synthetic.main.fragment_verify_token.signUp_tv
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass.
 */
class VerifyTokenFragment : BaseFragment() {

    private val viewModel: RecoverPasswordViewModel by viewModels()

    override var hasBackButton: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_token, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pegar lib de code phone

        useful.createLink(signUp_tv,
            "Já tem uma conta? Entre aqui",
            "Entre aqui", object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // your action

                    navigation.navigateUp()
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
            navigation.navigate(R.id.action_verifyTokenFragment_to_updatePasswordByTokenFragment)
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

                val bundle = Bundle()

                bundle.putString("idUser", responseInfo.id)

                if (responseInfo.status.equals("01")) {
                   // navigation.navigate(R.id.action_verifyTokenFragment_to_updatePasswordByTokenFragment, bundle)
                } else {
                    responseInfo.msg?.let {
                        singleToast.show(requireActivity(),
                            it, Toast.LENGTH_LONG)
                    }
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
                GenericDialogFragment.showConnectionError(requireActivity(), ::verifyTokenPressed, parentFragmentManager)
            }
        }
    }


    private fun verifyTokenPressed(v: View?) {
        KeyboardUtils.hideKeyboard(requireActivity())
        viewModel.verifyTokenPassword(
            tokenPass = token_et.text.toString())
    }
}