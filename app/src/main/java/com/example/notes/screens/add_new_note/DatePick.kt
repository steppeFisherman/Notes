package com.example.notes.screens.add_new_note

import android.app.DatePickerDialog
import android.view.View
import android.widget.Button
import java.text.DateFormat
import java.util.*

interface DatePick {

    fun obtainCalendar(view: View, selectedDate: (long: Long) -> Unit)

    class BaseDatePick : DatePick {

        override fun obtainCalendar(view: View, selectedDate: (long: Long) -> Unit) {
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

                    val text = DateFormat.getDateInstance()
                        .format(pickedDateTime.time)
                    (view as Button).text = text
                    val date = pickedDateTime.time.time
                    selectedDate(date)
                },
                startYear, startMonth, startDay,
            ).show()
        }
    }
}