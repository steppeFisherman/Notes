package com.example.notes.screens.main

import androidx.lifecycle.ViewModel
import com.example.notes.domain.usecases.FetchNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(fetchNotesUseCase: FetchNotesUseCase) :
    ViewModel() {

    val allNotes = fetchNotesUseCase.execute()
}


