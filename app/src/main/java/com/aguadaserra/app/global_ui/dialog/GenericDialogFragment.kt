package com.aguadaserra.app.global_ui.dialog


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.aguadaserra.app.GlobalApplication
import com.aguadaserra.app.R
import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import kotlinx.android.synthetic.main.fragment_generic_dialog.*


private const val ARG_DIALOG_TITLE = "ARG_DIALOG_TITLE"
private const val ARG_DIALOG_MESSAGE = "ARG_DIALOG_MESSAGE"
private const val ARG_DIALOG_LEFT_OPTION = "ARG_DIALOG_LEFT_OPTION"
private const val ARG_DIALOG_RIGHT_OPTION = "ARG_DIALOG_RIGHT_OPTION"


class GenericDialogFragment : DialogFragment() {

    private var onLeftButtonPressed: (() -> Unit)? = null
    private var onRightButtonPressed: (() -> Unit)? = null

    private var mTitle: String? = null
    private var mMessage: String? = null
    private var mLeftText: String? = null
    private var mRightText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.ThemeDialogCustom)

        arguments?.let {
            mTitle = it.getString(ARG_DIALOG_TITLE)
            mMessage = it.getString(ARG_DIALOG_MESSAGE)
            mLeftText = it.getString(ARG_DIALOG_LEFT_OPTION)
            mRightText = it.getString(ARG_DIALOG_RIGHT_OPTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_generic_dialog, container, false)
    }

    fun setOnLeftOptionPressed(action: () -> Unit): GenericDialogFragment {
        onLeftButtonPressed = action
        return this
    }

    fun setOnRightOptionPressed(action: () -> Unit): GenericDialogFragment {
        onRightButtonPressed = action
        return this
    }

    override fun onStart() {
        super.onStart()

        dialog!!.window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            it.setBackgroundDrawableResource(R.drawable.alert_gradient_bg)
//            it.setDimAmount(0f)
        }

        msgTitle.isVisible = mTitle != null
        msgTitle.text = mTitle
        msgText.text = mMessage

        val hasLeftButton = mLeftText != null

        leftBtn.isVisible = hasLeftButton

        if (hasLeftButton) {
            leftBtn.text = mLeftText
            leftBtn.setOnClickListener {
                onLeftButtonPressed?.invoke()
                dismiss()
            }

        }

        val hasRightButton = mRightText != null

        rightBtn.isVisible = hasRightButton

        if (hasRightButton) {
            rightBtn.text = mRightText
            rightBtn.setOnClickListener {
                onRightButtonPressed?.invoke()
                dismiss()
            }
        }
    }

    companion object {
        fun getInstance(
            title: String?,
            message: String,
            positiveOption: String,
            negativeOption: String? = null
        ): GenericDialogFragment {
            return GenericDialogFragment()
                .apply {
                    arguments = bundleOf(
                        ARG_DIALOG_TITLE to title,
                        ARG_DIALOG_MESSAGE to message,
                        ARG_DIALOG_LEFT_OPTION to negativeOption,
                        ARG_DIALOG_RIGHT_OPTION to positiveOption
                    )
                }
        }

        fun showErrorDialog(context: Context, title: String?, error: String, fragmentManager: FragmentManager) {
            val dialog =
                getInstance(
                    title,
                    error,
                    context.getString(R.string.understood)
                )
            dialog.show(fragmentManager, "error_dialog")
        }

        fun showErrorFromResponse(context: Context, error: ServiceResponse<*>, fragmentManager: FragmentManager) {

            if(error is ServiceResponse.Error.GenericError) {
                getInstance(
                    context.getString(R.string.error),
                    error.message ?: error.error ?: context.getString(R.string.foreign_error),
                    context.getString(R.string.understood)
                ).show(fragmentManager, "error_dialog")
            }
            else if(error is ServiceResponse.Error.NetworkError) {
                getInstance(
                    context.getString(R.string.no_connection),
                    context.getString(R.string.no_connection_description),
                    context.getString(R.string.understood)
                ).show(fragmentManager, "error_dialog")
            }
        }

        fun showConnectionError(context: Context, fragmentManager: FragmentManager, onRetry: () -> Unit, onCancel: (() -> Unit)? = null) {
            val instance = getInstance(
                context.getString(R.string.no_connection), context.getString(R.string.no_connection_description),
                context.getString(R.string.try_again),
                context.getString(R.string.cancel)
            )
            instance.isCancelable = false
            instance.onLeftButtonPressed = onCancel
            instance.onRightButtonPressed = onRetry
            instance.show(fragmentManager, "connection_error_dialog")
        }

        fun showConnectionError(context: Context, onRetryPressed: (View?) -> Unit, fragmentManager: FragmentManager, onCancel: (() -> Unit)? = null) {
            showConnectionError(context, fragmentManager, { onRetryPressed(null) }, onCancel)
        }
    }

}
