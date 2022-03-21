package com.example.notes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.notes.data.storage.firebase.FirebaseInstance
import com.example.notes.data.storage.models.NoteCloud
import com.example.notes.data.storage.room.AppRoomDao
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class NoteRepositoryImpl(
    private val appRoomDao: AppRoomDao,
    private val mapper: MapperNoteDB,
    private val firebase: FirebaseInstance
) : NoteRepository {

    override val allNotes: LiveData<List<NoteDomain>>
        get() = fetchCache()

    private fun fetchCache() = if (allNotes.value?.isEmpty() == true) {
        val query = firebase.noteCollectionRef().get().result
        val listNoteCloud = query?.toObjects(NoteCloud::class.java)
        val listNoteCache = listNoteCloud?.map {
            mapper.mapCloudToCache(it)
        }

        CoroutineScope(Job()).launch(Dispatchers.IO) {
            listNoteCache?.forEach {
                appRoomDao.insert(it)
            }
        } as LiveData<List<NoteDomain>>
    } else {
        appRoomDao.getAllNotes().map { listNoteCache ->
            listNoteCache.map { noteCache ->
                mapper.mapCacheToDomain(noteCache)
            }
        }
    }


//        val job = CoroutineScope.async(Dispatchers.IO){
//           val query = firebase.noteCollectionRef().get().result
//           val listNoteCloud = query?.toObjects(NoteCloud::class.java)
//
//            }


    // mAllNotes = firebase.noteCollectionRef().get()
//        }else{
//            appRoomDao.getAllNotes().map { listNoteCache ->
//                listNoteCache.map { noteCache ->
//                    mapper.mapCacheToDomain(noteCache)
//                }
//            }
//        }

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
        } catch (e: java.lang.Exception) {
        }
    }
}
