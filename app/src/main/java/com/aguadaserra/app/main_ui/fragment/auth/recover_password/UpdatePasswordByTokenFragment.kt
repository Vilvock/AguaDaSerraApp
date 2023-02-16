package com.aguadaserra.app.main_ui.fragment.auth.recover_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import kotlinx.android.synthetic.main.fragment_update_password_by_token.*

class UpdatePasswordByTokenFragment : BaseFragment() {


    private val viewModel: RecoverPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_password_by_token, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update_bt.setOnClickListener(::updatePasswordPressed)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {

            update_bt.isProgressVisible = it.isLoading

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
                    singleToast.show(
                        requireActivity(),
                        it, Toast.LENGTH_LONG
                    )
                }

                if (responseInfo.status.equals("01")) {
                    navigation.navigateUp()
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
                GenericDialogFragment.showConnectionError(requireActivity(), ::updatePasswordPressed, parentFragmentManager)
            }
        }
    }


    private fun updatePasswordPressed(v: View?) {

        KeyboardUtils.hideKeyboard(requireActivity())
//
//        viewModel.updatePasswordByToken(
//            idUser = requireArguments().getString("idUser")!!,
//            password = password_et.text.toString(),
//            coPassword = coPassword_et.text.toString()
//        )
    }
}