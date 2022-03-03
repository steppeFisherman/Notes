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

fun View.showSnack(message: String) {
    Snackbar
        .make(this, message, Snackbar.LENGTH_LONG).show()
    this.textAlignment = View.TEXT_ALIGNMENT_CENTER
}

// fun datePick(view: View) {
//
//        val currentDateTime = Calendar.getInstance()
//        val startYear = currentDateTime.get(Calendar.YEAR)
//        val startMonth = currentDateTime.get(Calendar.MONTH)
//        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
//
//        DatePickerDialog(
//            view.context,
//            { _, year, month, day ->
//
//                val pickedDateTime = Calendar.getInstance()
//                pickedDateTime.set(Calendar.YEAR, year)
//                pickedDateTime.set(Calendar.MONTH, month)
//                pickedDateTime.set(Calendar.DAY_OF_MONTH, day)
//
//                dateLongType = pickedDateTime.time.time
//                val dateStringType =
//                    DateFormat.getDateInstance().format(pickedDateTime.time)
//                view.text = dateStringType
//            },
//            startYear,
//            startMonth,
//            startDay
//        ).show()
//    }