package com.github.emmpann.core.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

object UIHelper {
    fun showSnackbar(view: View, msg: String) {
        Snackbar.make(
            view,
            msg,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun alertBuilder(context: Context, title: String, msg: String): MaterialAlertDialogBuilder =
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(msg)
}