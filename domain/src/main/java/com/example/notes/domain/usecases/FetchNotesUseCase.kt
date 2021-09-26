package com.example.notes.domain.usecases

import androidx.lifecycle.LiveData
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.repository.NoteRepository

class FetchNotesUseCase(private val noteRepository: NoteRepository) {

    fun execute(): LiveData<List<NoteDomain>> {
        return noteRepository.allNotes
    }
}