package com.test.paypaytest.theme.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

class Toast {

    fun Context.showToast(message: String, onDisplayChange: () -> Unit) {
        showToast(message, onDisplayChange)
    }

    fun Context.showToast(@StringRes message: Int, onDisplayChange: () -> Unit) {
        showToast(getString(message), onDisplayChange)
    }

    private fun Context.showToast(message: String, onDisplayChange: (Boolean) -> Unit) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).also {
            it.addCallback(object : Toast.Callback() {
                override fun onToastHidden() {
                    super.onToastHidden()
                    onDisplayChange(false)
                }

                override fun onToastShown() {
                    super.onToastShown()
                    onDisplayChange(true)
                }
            })
        }.show()
    }


}