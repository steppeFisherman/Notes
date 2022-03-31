package com.example.notes.domain.repository

import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.models.Result

interface NoteRepository {

    val allNotes: Result

    suspend fun insert(noteDomain: NoteDomain, onSuccess: () -> Unit)
    suspend fun delete(noteDomain: NoteDomain, onSuccess: () -> Unit)
}