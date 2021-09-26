package com.example.notes.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.domain.usecases.FetchNotesUseCase
import com.example.notes.utils.DAO

class MainFragmentViewModelFactory : ViewModelProvider.Factory {

    private val noteRepository by lazy(LazyThreadSafetyMode.NONE) {
        NoteRepositoryImpl(DAO)
    }

    private val fetchNotesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        FetchNotesUseCase(noteRepository = noteRepository)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(fetchNotesUseCase = fetchNotesUseCase) as T
    }
}