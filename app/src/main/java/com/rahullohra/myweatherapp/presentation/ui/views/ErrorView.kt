package com.rahullohra.myweatherapp.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.rahullohra.myweatherapp.R

class ErrorView : LinearLayout {

    val tvErrorTitle: AppCompatTextView
    val btnRetry: AppCompatButton
    var callback: ErrorViewCallback? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun getLayout() = R.layout.view_error

    init {
        val parent = LayoutInflater.from(context).inflate(getLayout(), this)
        tvErrorTitle = parent.findViewById(R.id.tv_error_title)
        btnRetry = parent.findViewById(R.id.btn_retry)

        setClicks()
    }

    fun setClicks() {
        btnRetry.setOnClickListener {
            callback?.onRetry()
        }
    }

    interface ErrorViewCallback {
        fun onRetry()
    }
}
