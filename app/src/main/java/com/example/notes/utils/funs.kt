package com.example.notes.utils

import android.annotation.SuppressLint
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

fun View.snack(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    //  this.textAlignment = View.TEXT_ALIGNMENT_CENTER

fun View.showSnackLong(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
  this.textAlignment = View.TEXT_ALIGNMENT_CENTER
}

fun View.showSnackIndefinite(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).show()
    this.textAlignment = View.TEXT_ALIGNMENT_CENTER
}

@SuppressLint("ShowToast")
fun View.dismissSnack() {
    Snackbar.make(this,"", Snackbar.LENGTH_LONG).dismiss()
}
