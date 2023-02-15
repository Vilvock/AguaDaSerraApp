package com.aguadaserra.app.global_ui.config_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs

abstract class BaseViewModelFragment<out T: ViewModel>(clazz: Class<T>): BaseFragment() {

    protected val viewModel: T by lazy { ViewModelProvider(this)[clazz] }

    open val args: NavArgs? = null

}