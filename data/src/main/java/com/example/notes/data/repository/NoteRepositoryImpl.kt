package com.example.notes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.notes.data.storage.AppRoomDao
import com.example.notes.data.storage.models.NoteData
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val appRoomDao: AppRoomDao
) : NoteRepository {

    override val allNotes: LiveData<List<NoteDomain>>
        get() = appRoomDao.getAllNotes().map { listNoteData ->
            listNoteData.map { noteData ->
                mapToDomain(noteData)
            }
        }

    override suspend fun insert(note: NoteDomain, onSuccess: () -> Unit) {
        val noteData = mapToStorage(note)
        appRoomDao.insert(noteData)
        onSuccess()
    }

    override suspend fun delete(note: NoteDomain, onSuccess: () -> Unit) {
        val noteData = mapToStorage(note)
        appRoomDao.delete(noteData)
        onSuccess()
    }

    private fun mapToStorage(noteDomain: NoteDomain): NoteData {
        return NoteData(
            id = noteDomain.id,
            name = noteDomain.name,
            text = noteDomain.text
        )
    }

    private fun mapToDomain(noteData: NoteData): NoteDomain {
        return NoteDomain(
            id = noteData.id,
            name = noteData.name,
            text = noteData.text
        )
    }
}
