package com.example.notes.domain.usecases

import androidx.lifecycle.LiveData
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository

interface FetchNotesUseCase {

    fun execute(): LiveData<List<NoteDomain>>

    class Base(private val noteRepository: NoteRepository) : FetchNotesUseCase {
        override fun execute(): LiveData<List<NoteDomain>> {
            return noteRepository.allNotes
        }
    }
}

