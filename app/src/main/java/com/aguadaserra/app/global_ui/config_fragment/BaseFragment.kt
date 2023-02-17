package com.aguadaserra.app.global_ui.config_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.aguadaserra.app.R
import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import com.aguadaserra.app.global_ui.components.SingleToast
import com.aguadaserra.app.global_ui.config_activity.SystemBarColor
import com.aguadaserra.app.global_ui.config_activity.ToolbarTint
import com.aguadaserra.app.global_ui.config_activity.BaseActivity
import com.aguadaserra.app.global_ui.dialog.GenericDialogFragment
import com.aguadaserra.app.global_ui.dialog.CustomDialogMessages
import com.aguadaserra.app.util.Animation
import com.aguadaserra.app.util.Preferences
import com.aguadaserra.app.util.Useful
import com.aguadaserra.app.util.validation.Validation

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    open var title: String = ""

    open var systemBarColor: SystemBarColor = SystemBarColor.DARK

    open var hasBackButton = false

    open var toolbarVisibility = false

    open var bottomNavigationVisibility = false

    //pega o padrao
    open var navController: Int = R.id.nav_host_fragment

    lateinit var customDialogMessages: CustomDialogMessages
    lateinit var singleToast: SingleToast
    lateinit var preferences: Preferences
    lateinit var navigation: NavController
    lateinit var validation: Validation
    lateinit var useful: Useful
    lateinit var animation: Animation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshFragmentStyle()

        useful = Useful(requireContext())
        preferences = Preferences(requireContext())
        customDialogMessages = CustomDialogMessages(requireContext())
        singleToast = SingleToast.INSTANCE
        validation = Validation(requireContext())
        animation = Animation(requireContext())

        navigation = requireActivity().findNavController(navController)
    }

    fun refreshFragmentStyle() {
        if (toolbarVisibility) {
            updateTitle(title)
            updateHasBackButton(hasBackButton)
            updateSystemBarColor(systemBarColor)
        } else {
            updateVisibilityToolbar()
        }

        if (bottomNavigationVisibility) {
            updateVisibilityBottomNavigation()
        }

    }

    fun updateVisibilityBottomNavigation() {
        (requireActivity() as? BaseActivity)?.updatebottomNavigationVisibility(true)
    }

    fun updateVisibilityToolbar() {
        (requireActivity() as? BaseActivity)?.updateToolbarVisibility(false)
    }

    fun updateHasBackButton(hasBackButton: Boolean) {
        this.hasBackButton = hasBackButton
        (requireActivity() as? BaseActivity)?.updateToolbar(hasBackButton)
    }

    fun updateTitle(title: String) {
        this.title = title
        (requireActivity() as? BaseActivity)?.updateToolbarTitle(title)
    }

    fun updateSystemBarColor(color: SystemBarColor) {
        this.systemBarColor = color
        (requireActivity() as? BaseActivity)?.updateSystemBarColor(color)
    }

//    fun getMainParentFragment(): Fragment? {
//        try {
//            val navHostFragment =
//                requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//            return navHostFragment.childFragmentManager.fragments[0]
//        } catch (e:Exception) {
//            return null
//        }
//    }

    open fun onBackPressed() {
        findNavController().popBackStack()
    }

}

fun Fragment.displayErrorAndOfferRetry(requestError: ServiceResponse.Error, retryWith: () -> Unit, onCancel: (() -> Unit)? = null) {
    when (requestError) {
        is ServiceResponse.Error.GenericError -> {
            GenericDialogFragment.showErrorDialog(
                requireActivity(),
                requireActivity().getString(R.string.error),
                requestError.message ?: "",
                parentFragmentManager
            )
        }
        is ServiceResponse.Error.NetworkError -> {
            GenericDialogFragment.showConnectionError(requireActivity(), parentFragmentManager, retryWith, onCancel)
        }
    }
}

fun Fragment.displayErrorAndOfferRetry(retryWith: (View?) -> Unit, requestError: ServiceResponse.Error, onCancel: (() -> Unit)? = null) {
    displayErrorAndOfferRetry(requestError, { retryWith(null) }, onCancel)
}