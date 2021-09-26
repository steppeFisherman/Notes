package com.example.notes.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.domain.usecases.DeleteNoteUseCase
import com.example.notes.utils.DAO

class NoteFragmentViewModelFactory : ViewModelProvider.Factory {

    private val noteRepository by lazy(LazyThreadSafetyMode.NONE) {
        NoteRepositoryImpl(DAO)
    }

    private val deleteNoteUseCase by lazy(LazyThreadSafetyMode.NONE) {
        DeleteNoteUseCase(noteRepository = noteRepository)
    }

    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteFragmentViewModel(deleteNoteUseCase = deleteNoteUseCase) as T
    }
}