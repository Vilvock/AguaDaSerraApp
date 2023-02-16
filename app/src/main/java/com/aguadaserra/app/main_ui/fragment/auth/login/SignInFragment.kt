package com.aguadaserra.app.main_ui.fragment.auth.login

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
import kotlinx.android.synthetic.main.fragment_sign_in.*
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : BaseFragment() {

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        useful.createLink(signUp_tv,
            "Ainda não possui uma conta? Entre aqui",
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
                    ds.isUnderlineText = false
                }
            })

        useful.createLink(forgetPass_tv,
            "Esqueceu sua senha? Clique aqui",
            "Clique aqui", object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // your action
                    navigation.navigate(R.id.action_signInFragment_to_recoverPasswordFragment)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    // this is where you set link color, underline, typeface etc.
                    val linkColor = ContextCompat.getColor(requireActivity(), R.color.colorPrimary)
                    ds.color = linkColor
                    ds.isUnderlineText = false
                }
            })

        login_bt.setOnClickListener {
            loginPressed(it)
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            login_bt.isProgressVisible = it.isLoading
        })

        viewModel.loginResponseLiveData.observe(viewLifecycleOwner, ::onLoginResponse)

        configEditText()
    }

    override fun onStart() {
        super.onStart()

        viewModel.setupGenericErrorDisplay(requireActivity(), viewLifecycleOwner, parentFragmentManager)
    }

    private fun onLoginResponse(response: ServiceResponse<List<User>>) {
        when (response) {
            is ServiceResponse.Success -> {

                val responseInfo = response.value!![0]

                responseInfo.msg?.let {
                    singleToast.show(requireActivity(),
                        it, Toast.LENGTH_LONG)
                }

//                if (responseInfo.status.equals("01")) {
//                    preferences.setLogin(true)
//                    preferences.setUserData(responseInfo)
//
//                    navigation = requireActivity().findNavController(R.id.nav_host_fragment)
//
//                    val graph = navigation.navInflater.inflate(R.navigation.main_graph)
//                    graph.setStartDestination(R.id.homeFragment)
//                    navigation.graph = graph
//
//                }

            }
            is ServiceResponse.Error.GenericError -> {
                //Verifica se é um codigo de erro conhecido
                val errorMsg = when(response.message) {
                    else -> getString(R.string.no_connection_description)
                }

                GenericDialogFragment.showErrorDialog(requireActivity(), getString(R.string.attention), errorMsg, parentFragmentManager)
            }
            is ServiceResponse.Error.NetworkError -> {
                GenericDialogFragment.showConnectionError(requireActivity(), ::loginPressed, parentFragmentManager)
            }
        }
    }

    private fun loginPressed(v: View?) {
        KeyboardUtils.hideKeyboard(requireActivity())
        viewModel.login(
            email = email_et.text.toString(),
            password = password_et.text.toString())
    }

    private fun configEditText(){

        val email = email_et

        email_et.onFocusChangeListener = View.OnFocusChangeListener { v, hasfoucs ->
            if (!hasfoucs) {
                KeyboardUtils.hideKeyboard(requireActivity())
            }
        }

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                emailChecked_iv.setImageResource(R.drawable.ic_checked_false)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                emailChecked_iv.setImageResource(R.drawable.ic_checked_false)
            }

            override fun afterTextChanged(p0: Editable?) {
                if (isEmailValidEditTextType(email)) {
//                    emailChecked_iv.setImageResource(R.drawable.ic_checked)
                   // Toast.makeText(requireContext(), "Email Valido!", Toast.LENGTH_SHORT).show()
                } else {
//                    emailChecked_iv.setImageResource(R.drawable.ic_checked_false)
                }
            }

        })
    }
    fun isEmailValidEditTextType(email: EditText): Boolean {
        return try {
            val pattern =
                Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            val matcher = pattern.matcher(email.text)
            matcher.matches()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }
}