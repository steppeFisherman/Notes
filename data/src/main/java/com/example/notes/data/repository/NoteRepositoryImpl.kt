package com.example.notes.data.repository

import androidx.lifecycle.map
import com.example.notes.data.storage.firebase.FirebaseInstance
import com.example.notes.data.storage.room.AppRoomDao
import com.example.notes.domain.models.ErrorType
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.models.Result
import com.example.notes.domain.repository.NoteRepository
import com.google.firebase.FirebaseApiNotAvailableException
import kotlinx.coroutines.tasks.await
import java.net.UnknownHostException

class NoteRepositoryImpl(
    private val appRoomDao: AppRoomDao,
    private val mapper: MapperNoteDB,
    private val firebase: FirebaseInstance
) : NoteRepository {

    override val allNotes: Result
        get() = try {
            Result.Success(appRoomDao.getAllNotes().map { listNoteCache ->
                listNoteCache.map { noteCache ->
                    mapper.mapCacheToDomain(noteCache)
                }
            })
        } catch (e: Exception) {
            Result.Fail(
                when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is FirebaseApiNotAvailableException -> ErrorType.SERVICE_UNAVAILABLE
                    is NullPointerException -> ErrorType.NULL_POINTER_EXCEPTION
                    else -> ErrorType.GENERIC_ERROR
                }
            )
        }

    override suspend fun insert(noteDomain: NoteDomain, onSuccess: () -> Unit) {
        val noteCache = mapper.mapDomainToCache(noteDomain)
        val noteCloud = mapper.mapDomainToCloud(noteDomain)
        try {
            val id = firebase.noteCollectionRef().add(noteCloud).await().id
            firebase.noteCollectionRef().document(id).update("firebaseId", id).await()
            noteCache.firebaseId = id
            appRoomDao.insert(noteCache)
            onSuccess()
        } catch (e: Exception) {
        }
    }

    override suspend fun delete(noteDomain: NoteDomain, onSuccess: () -> Unit) {
        val noteCache = mapper.mapDomainToCache(noteDomain)
        val noteCloud = mapper.mapDomainToCloud(noteDomain)
        try {
            firebase.noteCollectionRef()
                .document(noteCloud.firebaseId).delete().await()
            appRoomDao.delete(noteCache)
            onSuccess()
        } catch (e: Exception) {
        }
    }
}
