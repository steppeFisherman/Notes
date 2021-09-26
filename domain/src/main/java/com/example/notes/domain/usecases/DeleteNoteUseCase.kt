package com.example.notes.domain.usecases

import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(noteDomain: NoteDomain, onSuccess:()->Unit) {
        noteRepository.delete(noteDomain) {
            onSuccess()
        }
    }
}