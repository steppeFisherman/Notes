package com.example.notes.domain.repository

import androidx.lifecycle.LiveData
import com.example.notes.domain.models.NoteDomain

interface NoteRepository {

    val allNotes: LiveData<List<NoteDomain>>

    suspend fun insert(note: NoteDomain, onSuccess: () -> Unit)
    suspend fun delete(note: NoteDomain, onSuccess: () -> Unit)

  //  suspend fun insert(note: AppNote, onSuccess: () -> Unit)
}