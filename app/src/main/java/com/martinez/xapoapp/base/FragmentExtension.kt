package com.martinez.xapoapp.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.martinez.xapoapp.R

fun Fragment.showDialogWithMessage(
    @StringRes titleId: Int,
    @StringRes messageId: Int,
    @StringRes buttonTextId: Int,
    onNegativeClickListener: (() -> Unit) = {},
    cancelable: Boolean = true,
    onDismissListener: (() -> Unit) = {}
) {
    context?.let {
        AlertDialog.Builder(it).apply {
            setTitle(titleId)
            setMessage(messageId)
            setNegativeButton(buttonTextId) { _, _ -> onNegativeClickListener() }
            setOnDismissListener { onDismissListener() }
            setCancelable(cancelable)
            show()
        }
    }
}

fun Fragment.showSomethingWentWrongDialog(onDismissListener: (() -> Unit) = {}) {
    context?.let {
        AlertDialog.Builder(it).apply {
            setMessage(R.string.error__something_went_wrong)
            setNegativeButton(R.string.error__close_button, null)
            setOnDismissListener { onDismissListener() }
            show()
        }
    }
}

