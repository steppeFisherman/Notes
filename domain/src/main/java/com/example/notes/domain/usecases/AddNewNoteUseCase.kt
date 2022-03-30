package com.example.notes.domain.usecases

import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository

interface AddNewNoteUseCase {

    suspend fun execute(noteDomain: NoteDomain, onSuccess: () -> Unit)

    class Base(private val noteRepository: NoteRepository) : AddNewNoteUseCase {
        override suspend fun execute(noteDomain: NoteDomain, onSuccess: () -> Unit) {
            noteRepository.insert(noteDomain) {
                onSuccess()
            }
        }
    }
}