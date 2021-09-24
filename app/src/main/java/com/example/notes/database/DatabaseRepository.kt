package com.example.notes.database

import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote

interface DatabaseRepository {

    val allNotes: LiveData<List<AppNote>>

    suspend fun insert(note: AppNote, onSuccess: () -> Unit)
    suspend fun delete(note: AppNote, onSuccess: () -> Unit)
}