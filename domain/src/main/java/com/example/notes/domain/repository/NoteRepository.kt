package com.example.notes.domain.repository

import androidx.lifecycle.LiveData
import com.example.notes.domain.models.NoteDomain

interface NoteRepository {

    val allNotes: LiveData<List<NoteDomain>>

    suspend fun insert(noteDomain: NoteDomain, onSuccess: () -> Unit)
    suspend fun delete(noteDomain: NoteDomain, onSuccess: () -> Unit)

}