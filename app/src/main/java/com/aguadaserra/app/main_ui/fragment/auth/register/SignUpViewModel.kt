package com.aguadaserra.app.main_ui.fragment.auth.register

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.*
import com.aguadaserra.app.ApplicationConstants
import com.aguadaserra.app.ApplicationConstants.LOCALE_PT_BR
import com.aguadaserra.app.controller.user.UserControl
import com.aguadaserra.app.controller.user.UserServiceInterface
import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import com.aguadaserra.app.global_model.User
import com.aguadaserra.app.global_ui.config_fragment.BaseAndroidViewModel
import com.aguadaserra.app.util.extension.setValueAndNotify
import com.baedigital.app.util.SingleLiveEvent
import com.aguadaserra.app.util.validation.CPFValidator
import com.aguadaserra.app.util.validation.CPFValidatorInterface
import com.aguadaserra.app.util.validation.LoginValidator
import com.aguadaserra.app.util.validation.LoginValidatorInterface
import kotlinx.coroutines.*
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class SignUpViewModel(
    application: Application,
    private val cpfValidator: CPFValidatorInterface = CPFValidator(),
    private val loginValidator: LoginValidatorInterface = LoginValidator(),
    private val userControl: UserServiceInterface = UserControl()
) : BaseAndroidViewModel<SignUpViewModel.ViewState>(application, ViewState()) {

    val registerResponseLiveData: LiveData<ServiceResponse<List<User>>> = SingleLiveEvent()

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", LOCALE_PT_BR)

    constructor(application: Application) : this(
        application,
        CPFValidator(),
        LoginValidator(),
        UserControl()
    )

    data class ViewState(
        val passionsList: List<User>? = null,
        val genresList: List<User>? = null,
        var isLoading: Boolean = false
    )

    fun listGenres() {

        viewModelScope.launch(Dispatchers.IO) {

            val user = User()

            user.token = ApplicationConstants.TOKEN

            val result = userControl.listGenres(user)

            if (result is ServiceResponse.Success) {
                _viewState.postValue(_viewState.value?.copy(genresList = result.value!!))
            } else if (result is ServiceResponse.Error) {
                _viewState.postValue(_viewState.value?.copy(genresList = null))
            }
        }

    }

    fun listPassions() {

        viewModelScope.launch(Dispatchers.IO) {

            val user = User()

            user.token = ApplicationConstants.TOKEN

            val result = userControl.listPassions(user)

            if (result is ServiceResponse.Success) {
                _viewState.postValue(_viewState.value?.copy(passionsList = result.value!!))
            } else if (result is ServiceResponse.Error) {
                _viewState.postValue(_viewState.value?.copy(passionsList = null))
            }
        }

    }

    fun register(
        name: String,
        email: String,
        cellphone: String?,
        birth: String,
        genreType: String,
        password: String,
        code: String,
        latitude: String,
        longitude: String,
        passionList: List<String>) {

        val user = User()

        user.name = name
        user.email = email
        user.cellphone = cellphone
        user.password = password
        user.birth = birth
        user.idGenreType = genreType
        user.passionList = passionList

        if (code.isNotBlank()) {
            user.personCode = code
        }

        user.latitude = latitude
        user.longitude = longitude

        user.token = ApplicationConstants.TOKEN

        viewModelScope.launch(Dispatchers.IO) {
            _viewState.setValueAndNotify { isLoading = true }
            val result = userControl.register(user)
            (registerResponseLiveData as SingleLiveEvent).postValue(result)
            _viewState.setValueAndNotify { isLoading = false }
        }


    }

    fun verifyEmail(email: String): LiveData<ServiceResponse<List<User>>> {

        return liveData(Dispatchers.IO) {

            val user = User()

            user.email = email
            user.token = ApplicationConstants.TOKEN

            val result = userControl.verifyEmail(user)

            emit(result)

        }
    }

    fun loginExternal(name: String,
                    email: String,
                    cellphone: String,
                    genre: String,
                    birth: String,
                    latitude: String,
                    longitude: String,
                    url: File,
                    passionList: List<String>) {

        viewModelScope.launch(Dispatchers.IO) {

            _viewState.setValueAndNotify { isLoading = true }
            val result = userControl.loginExternal(
                name = name,
                email = email,
                cellphone = cellphone,
                genre = genre,
                birth = birth,
                latitude = latitude,
                longitude = longitude,
                token = ApplicationConstants.TOKEN,
                url = url,
                passionList = passionList)

            //enviar resultado pro onresponse
            (registerResponseLiveData as SingleLiveEvent).postValue(result)
            _viewState.setValueAndNotify { isLoading = false }
        }

    }

    fun validateName(name: String?): Boolean {
        return (!name.isNullOrBlank()).also {valid ->
            if (!valid) {
                setErrorMessage("Atenção", "Nome inválido.")
            }
        }
    }

    fun validateSurname(surname: String?): Boolean {
        return (!surname.isNullOrBlank()).also {valid ->
            if (!valid) {
                setErrorMessage("Atenção", "Sobrenome inválido.")
            }
        }
    }

    fun validateCellphone(cellphone: String?): Boolean {
        return (!cellphone.isNullOrBlank()).also {valid ->
            if (!valid) {
                setErrorMessage("Atenção", "Celular inválido.")
            }
        }
    }

    fun validateEmail(email: String?): Boolean {
        if (email != ""){
            return  if (loginValidator.isEmailValid(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                true
            } else {
                setErrorMessage("Atenção", "E-mail inválido.")
                false
            }
        }else{
            setErrorMessage("Atenção", "E-mail inválido.")
           return false
        }

    }


    fun validateBirthDate(birthDate:String?) : Boolean {

        val today = Calendar.getInstance()

        try {

            val parsedDate = dateFormat.parse(birthDate ?: "")

            val has4DigitYear = Calendar.getInstance().apply { time = parsedDate }.get(Calendar.YEAR) > 1000

            return if(parsedDate != null && parsedDate.time < today.timeInMillis && has4DigitYear) {
                true
            } else {
                setErrorMessage("Atenção", "Você precisa informar uma data de nascimento válida.")
                false
            }

        } catch (e: Exception) {
            setErrorMessage("Atenção", "Você precisa informar uma data de nascimento válida.")
            return false
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

    fun validatePasswordMatch(password1: String?, password2: String?): Boolean {
        return if (password1 == password2) {
            true
        } else {
            setErrorMessage("Atenção", "As senhas não são iguais.")
            false
        }
    }

    fun validateGenericEditText(editText: EditText, nameText: String): Boolean {
        return (editText.text.toString().isNotBlank()).also { valid ->
            if (!valid) {
                setErrorMessage("Atenção", "$nameText não pode estar vazio.")
            }
        }
    }

}