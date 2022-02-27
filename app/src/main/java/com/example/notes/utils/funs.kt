package com.example.notes.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun hideKeyboard(activity: Activity, view: View) {
    val imm = activity
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showSnack(view: View, message: String) {
    Snackbar
        .make(view, message, Snackbar.LENGTH_LONG).show()
    view.textAlignment = View.TEXT_ALIGNMENT_CENTER
}