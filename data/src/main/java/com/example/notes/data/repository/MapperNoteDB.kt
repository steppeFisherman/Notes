package com.example.notes.data.repository

import com.example.notes.data.storage.models.NoteDB
import com.example.notes.domain.models.NoteDomain

interface MapperNoteDB {

    fun mapToStorage(noteDomain: NoteDomain): NoteDB
    fun mapToDomain(noteDB: NoteDB): NoteDomain

    class Base : MapperNoteDB {

        override fun mapToStorage(noteDomain: NoteDomain): NoteDB {
            return NoteDB(
                id = noteDomain.id,
                name = noteDomain.name,
                text = noteDomain.text
            )
        }

        override fun mapToDomain(noteDB: NoteDB): NoteDomain {
            return NoteDomain(
                id = noteDB.id,
                name = noteDB.name,
                text = noteDB.text
            )
        }
    }
}