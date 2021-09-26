package com.example.notes.screens.main

import androidx.lifecycle.ViewModel
import com.example.notes.domain.usecases.FetchNotesUseCase
import com.example.notes.utils.REPOSITORY

class MainFragmentViewModel(fetchNotesUseCase: FetchNotesUseCase)
    : ViewModel() {
    val allNotes = fetchNotesUseCase.execute()
}