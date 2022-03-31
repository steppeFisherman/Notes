package com.example.notes.domain.usecases

import com.example.notes.domain.models.Result
import com.example.notes.domain.repository.NoteRepository

interface FetchNotesUseCase {

    fun execute(): Result

    class Base(private val noteRepository: NoteRepository) : FetchNotesUseCase {
        override fun execute(): Result {
            return noteRepository.allNotes
        }
    }
}

