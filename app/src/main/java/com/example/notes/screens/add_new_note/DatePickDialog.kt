package com.example.notes.screens.add_new_note

import android.app.DatePickerDialog
import android.view.View
import android.widget.Button
import java.text.DateFormat
import java.util.*

interface DatePickDialog {

    fun obtainCalendar(view: View): Long

    class Base : DatePickDialog {

        override fun obtainCalendar(view: View): Long {

            var dateLongType: Long = 0
            val currentDateTime = Calendar.getInstance()
            val startYear = currentDateTime.get(Calendar.YEAR)
            val startMonth = currentDateTime.get(Calendar.MONTH)
            val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                view.context,
                { _, year, month, day ->

                    val pickedDateTime = Calendar.getInstance()
                    pickedDateTime.set(Calendar.YEAR, year)
                    pickedDateTime.set(Calendar.MONTH, month)
                    pickedDateTime.set(Calendar.DAY_OF_MONTH, day)

                    dateLongType = pickedDateTime.time.time
                    val dateStringType =
                        DateFormat.getDateInstance().format(pickedDateTime.time)
                    (view as Button).text = dateStringType
                },
                startYear,
                startMonth,
                startDay
            ).show()
            return dateLongType
        }
    }
}