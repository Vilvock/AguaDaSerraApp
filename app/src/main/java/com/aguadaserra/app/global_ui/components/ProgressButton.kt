package com.aguadaserra.app.global_ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity.CENTER
import android.view.Gravity.NO_GRAVITY
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.aguadaserra.app.R
import me.zhanghai.android.materialprogressbar.MaterialProgressBar


class ProgressButton : FrameLayout {

    private val BUTTON_HEIGHT_DP = 60

    private val validAttributeSet = intArrayOf(
        android.R.attr.background, // idx 0
        android.R.attr.text,        // idx 1
        android.R.attr.textColor,
        android.R.attr.textSize,
        android.R.attr.fontFamily,
        android.R.attr.tint
    )

    private var button: TextView = TextView(context)
    private lateinit var progressBar: MaterialProgressBar
    private var attr_buttonText: String? = null
    private var attr_background: Drawable? = null
    private var attr_buttonTextColor: ColorStateList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
    @ColorInt
    private var attr_progressTint: Int = Color.WHITE

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeButton(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, intDefStyle: Int) : super(
        context,
        attrs,
        intDefStyle
    ) {
        initializeButton(attrs)
    }

    var isProgressVisible: Boolean
        get() = progressBar.isVisible
        set(value) {
            if (value) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        }

    @SuppressLint("ResourceType")
    private fun initializeButton(attrs: AttributeSet?) {

        isClickable = true
        isFocusable = true

        clipToPadding = false
        clipChildren = false

        val ta = context.obtainStyledAttributes(attrs, validAttributeSet)

        val taPb = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton)

        val fontSize = taPb.getDimensionPixelSize(R.styleable.ProgressButton_pb_textSize, 0)
        attr_progressTint = taPb.getColor(R.styleable.ProgressButton_pb_loaderTint, Color.BLACK)

        taPb.recycle()

        attr_background = ta.getDrawable(0)
        attr_buttonText = ta.getString(1)

        setBackgroundColor(Color.TRANSPARENT)

        //para deixar bold
//        val builder = SpannableStringBuilder(attr_buttonText)
//        val boldStyle = StyleSpan(Typeface.BOLD)
//        builder.setSpan(boldStyle, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        button.text = attr_buttonText
        button.gravity = CENTER
        button.background = attr_background
        button.layoutParams =
            LayoutParams(MATCH_PARENT, dpToPx(BUTTON_HEIGHT_DP).toInt(), NO_GRAVITY)

        button.typeface = ResourcesCompat.getFont(context, R.font.inter_bold)
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize.toFloat())

        button.isAllCaps = false
        button.setTextColor(attr_buttonTextColor)

        addView(button)

        progressBar = MaterialProgressBar(context)

        val progressSize = dpToPx(25).toInt()
        progressBar.layoutParams = LayoutParams(progressSize, progressSize, CENTER)
        progressBar.supportIndeterminateTintList = ColorStateList.valueOf(attr_progressTint)
        progressBar.visibility = View.GONE
        addView(progressBar)

        ViewCompat.setElevation(progressBar, ViewCompat.getElevation(button) + dpToPx(10))

        ta.recycle()
    }


    private fun dpToPx(dp: Int) = dp * resources.displayMetrics.density

    fun showProgressBar() {
        button.text = ""
        button.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        button.text = attr_buttonText
        button.isEnabled = true
        progressBar.visibility = View.GONE
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }

    override fun hasOnClickListeners(): Boolean {
        return button.hasOnClickListeners()
    }

    override fun isEnabled(): Boolean {
        return button.isEnabled
    }

    override fun setEnabled(enabled: Boolean) {
        button.isEnabled = enabled
    }

    fun setText(text: String) {
        attr_buttonText = text
        if (button.isEnabled) {
            button.text = text
        }
    }
}
