package com.aguadaserra.app.main_ui.fragment.auth.login

import android.app.Application
import androidx.lifecycle.*
import com.aguadaserra.app.ApplicationConstants
import com.aguadaserra.app.controller.user.UserControl
import com.aguadaserra.app.controller.user.UserServiceInterface
import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import com.aguadaserra.app.global_model.User
import com.aguadaserra.app.global_ui.config_fragment.BaseAndroidViewModel
import com.aguadaserra.app.util.extension.setValueAndNotify
import com.aguadaserra.app.util.validation.LoginValidator
import com.aguadaserra.app.util.validation.LoginValidatorInterface
import com.baedigital.app.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
    application: Application,
    private val loginValidator: LoginValidatorInterface = LoginValidator(),
    private val userControl: UserServiceInterface = UserControl()
) : BaseAndroidViewModel<SignInViewModel.ViewState>(application, ViewState()) {

    val loginResponseLiveData: LiveData<ServiceResponse<List<User>>> = SingleLiveEvent()

    constructor(application: Application) : this(
        application,
        LoginValidator(),
        UserControl()
    )

    //serve para fazer alterações
    data class ViewState(
        var email: String? = null,
        var password: String? = null,
        var isLoading: Boolean = false
    )

    //login singlelivevent
    fun login(email: String, password: String) {

        if (!validateEmail(email))
            return

        if (!validatePassword(password))
            return

        val user = User()

        user.email = email
        user.password = password
        user.token = ApplicationConstants.TOKEN

        viewModelScope.launch(Dispatchers.IO) {
            _viewState.setValueAndNotify { isLoading = true }
            val result = userControl.login(user)
            (loginResponseLiveData as SingleLiveEvent).postValue(result)
            _viewState.setValueAndNotify { isLoading = false }
        }
    }


    fun validateEmail(email: String?): Boolean {
        return if (loginValidator.isEmailValid(email)) {
            true
        } else {
            setErrorMessage("Atenção", "E-mail inválido.")
            false
        }
    }

    fun validatePassword(password: String?): Boolean {
        return if (!password.isNullOrBlank()) {
            true
        } else {
            setErrorMessage("Atenção", "Senha inválida.")
            false
        }
    }

}