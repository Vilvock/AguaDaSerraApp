package com.aguadaserra.app.global_ui.config_fragment

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aguadaserra.app.GlobalApplication
import com.aguadaserra.app.R
import com.aguadaserra.app.global_model.ErrorMessage
import com.aguadaserra.app.global_ui.dialog.GenericDialogFragment
import com.baedigital.app.util.SingleLiveEvent

abstract class BaseAndroidViewModel<T>(application: Application, viewState: T) :
    AndroidViewModel(application) {

    private val globalApplication = GlobalApplication()

    protected val _viewState = MutableLiveData<T>(viewState)
    val viewState: LiveData<T> = _viewState

    protected val _errorLiveData = SingleLiveEvent<ErrorMessage>()
    val errorLiveData: LiveData<ErrorMessage> = _errorLiveData

    protected val _connectionErrorLiveData = SingleLiveEvent<ErrorMessage>()
    val connectionErrorLiveData: LiveData<ErrorMessage> = _connectionErrorLiveData

    protected fun displayConnectionErrorMessage() {
        _connectionErrorLiveData.postValue(
            ErrorMessage(
                globalApplication.getAppResources()!!.getString(R.string.no_connection),
                globalApplication.getAppResources()!!.getString(R.string.no_connection_description)
            )
        )
    }

    fun setErrorMessage(title: String?, message: String) {
        _errorLiveData.postValue(ErrorMessage(title, message))
    }

    fun setupGenericErrorDisplay(context: Context, lifecycleOwner: LifecycleOwner, fragmentManager: FragmentManager) {
        errorLiveData.observe(lifecycleOwner, Observer {
            GenericDialogFragment.showErrorDialog(context, it.title, it.message, fragmentManager)
        })
    }

}