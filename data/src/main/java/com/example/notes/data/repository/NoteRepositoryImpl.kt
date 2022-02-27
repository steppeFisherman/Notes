package com.example.notes.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.notes.data.storage.firebase.FirebaseInstance
import com.example.notes.data.storage.room.AppRoomDao
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository
import kotlinx.coroutines.tasks.await

class NoteRepositoryImpl(
    private val appRoomDao: AppRoomDao,
    private val mapper: MapperNoteDB,
    private val firebase: FirebaseInstance
) : NoteRepository {

    override val allNotes: LiveData<List<NoteDomain>>
        get() = appRoomDao.getAllNotes().map { listNoteData ->
            listNoteData.map { noteDB ->
                mapper.mapCacheToDomain(noteDB)
            }
        }

    override suspend fun insert(noteDomain: NoteDomain, onSuccess: () -> Unit) {

        val noteCache = mapper.mapDomainToCache(noteDomain)
        val noteCloud = mapper.mapDomainToCloud(noteDomain)

        try {
            firebase.noteCollectionReference().add(noteCloud).await()
            appRoomDao.insert(noteCache)
            onSuccess()
        } catch (e: Exception) {}
    }

    override suspend fun delete(noteDomain: NoteDomain, onSuccess: () -> Unit) {
        val noteCache = mapper.mapDomainToCache(noteDomain)
        val noteCloud = mapper.mapDomainToCloud(noteDomain)
        try {
            appRoomDao.delete(noteCache)
            onSuccess()
        } catch (e: java.lang.Exception) {
        }
    }
}
