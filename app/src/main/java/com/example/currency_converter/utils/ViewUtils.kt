package com.example.currency_converter.utils

import android.view.View

class ViewUtils {

    public companion object {

        fun setEnabled(vararg views: View) {

            for (view in views) {
                view.isEnabled = true
            }
        }

        fun setDisabled(vararg views: View) {

            for (view in views) {
                view.isEnabled = false
            }

        }
    }
}