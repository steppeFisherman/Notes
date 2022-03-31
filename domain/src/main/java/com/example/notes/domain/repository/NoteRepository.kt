package com.example.notes.domain.repository

import androidx.lifecycle.LiveData
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.models.Result

interface NoteRepository {

  //  val allNotes: LiveData<List<NoteDomain>>
    val allNotes: Result

    suspend fun insert(noteDomain: NoteDomain, onSuccess: () -> Unit)
    suspend fun delete(noteDomain: NoteDomain, onSuccess: () -> Unit)
}