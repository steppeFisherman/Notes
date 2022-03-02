package com.example.notes.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.notes.domain.usecases.FetchNotesUseCase
import com.example.notes.model.MapperNoteApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    fetchNotesUseCase: FetchNotesUseCase,
    private val mapper: MapperNoteApp
) : ViewModel() {

    val allNotes = fetchNotesUseCase.execute().map { listNoteDomain ->
        listNoteDomain.map { mapper.mapDomainToApp(it) }
    }
}


