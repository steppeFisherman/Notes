package com.example.notes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.notes.data.storage.AppRoomDao
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val appRoomDao: AppRoomDao,
    private val mapper: MapperNoteDB
) : NoteRepository {

    override val allNotes: LiveData<List<NoteDomain>>
        get() = appRoomDao.getAllNotes().map { listNoteData ->
            listNoteData.map { noteDB ->
                mapper.mapToDomain(noteDB)
            }
        }

    override suspend fun insert(noteDomain: NoteDomain, onSuccess: () -> Unit) {
        val noteDB = mapper.mapToStorage(noteDomain)
        appRoomDao.insert(noteDB)
        onSuccess()
    }

    override suspend fun delete(noteDomain: NoteDomain, onSuccess: () -> Unit) {
        val noteDB = mapper.mapToStorage(noteDomain)
        appRoomDao.delete(noteDB)
        onSuccess()
    }
}
