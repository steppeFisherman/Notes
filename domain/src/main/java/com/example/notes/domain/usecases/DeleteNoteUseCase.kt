package com.example.notes.domain.usecases

import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository

interface DeleteNoteUseCase {

    suspend fun execute(noteDomain: NoteDomain, onSuccess: () -> Unit)

    class Base(private val noteRepository: NoteRepository) : DeleteNoteUseCase {
        override suspend fun execute(noteDomain: NoteDomain, onSuccess: () -> Unit) {
            noteRepository.delete(noteDomain) {
                onSuccess()
            }
        }
    }
}