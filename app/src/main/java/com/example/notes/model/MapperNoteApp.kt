package com.example.notes.model

import com.example.notes.domain.models.NoteDomain
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
                performDate = noteDomain.performDate,
                performed = noteDomain.performed
            )

        override fun mapAppToDomain(noteApp: NoteApp) =
            NoteDomain(
                id = noteApp.id,
                firebaseId = noteApp.firebaseId,
                name = noteApp.name,
                text = noteApp.text,
                performDate = noteApp.performDate,
                performed = noteApp.performed
            )

        val currentDateTime = Calendar.getInstance().time.time
    }
}