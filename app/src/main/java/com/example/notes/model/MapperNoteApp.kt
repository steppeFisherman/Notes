package com.example.notes.model

import android.annotation.SuppressLint
import com.example.notes.domain.models.NoteDomain
import java.text.SimpleDateFormat
import java.util.*

interface MapperNoteApp {

    fun mapDomainToApp(noteDomain: NoteDomain): NoteApp
    fun mapAppToDomain(noteApp: NoteApp): NoteDomain

    class Base : MapperNoteApp {

        override fun mapDomainToApp(noteDomain: NoteDomain) =
            NoteApp(
                id = noteDomain.id,
                firebaseId = noteDomain.firebaseId,
                name = noteDomain.name,
                text = noteDomain.text,
                performDate = convertLongToString(noteDomain.performDate),
                performed = noteDomain.performed
            )

        override fun mapAppToDomain(noteApp: NoteApp) =
            NoteDomain(
                id = noteApp.id,
                firebaseId = noteApp.firebaseId,
                name = noteApp.name,
                text = noteApp.text,
                performDate = convertStringToLong(noteApp.performDate),
                performed = noteApp.performed
            )

        @SuppressLint("SimpleDateFormat")
        fun convertLongToString(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd MMMM yyyy")
            return format.format(date)
        }

        @SuppressLint("SimpleDateFormat")
        fun convertStringToLong(date: String): Long {
            val df = SimpleDateFormat("dd MMMM yyyy")
            return df.parse(date)?.time ?: 0
        }
    }
}

