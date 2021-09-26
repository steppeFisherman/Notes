package com.example.notes.screens.add_new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.domain.usecases.AddNewNoteUseCase
import com.example.notes.utils.DAO

class AddNewNoteViewModelFactory : ViewModelProvider.Factory {

    private val noteRepository by lazy(LazyThreadSafetyMode.NONE) {
        NoteRepositoryImpl(DAO)
    }

    private val addNewNoteUseCase by lazy(LazyThreadSafetyMode.NONE) {
        AddNewNoteUseCase(noteRepository = noteRepository)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddNewNoteViewModel(addNewNoteUseCase = addNewNoteUseCase) as T
    }
}