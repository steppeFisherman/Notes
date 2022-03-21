package com.example.notes.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.notes.data.storage.firebase.FirebaseInstance
import com.example.notes.data.storage.models.NoteCloud
import com.example.notes.di.ToDispatch
import com.example.notes.domain.usecases.FetchNotesUseCase
import com.example.notes.model.MapperNoteApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    fetchNotesUseCase: FetchNotesUseCase,
    private val baseDispatcher: ToDispatch,
    private val mapper: MapperNoteApp,
) : ViewModel() {

    val allNotes = fetchNotesUseCase.execute().map { listNoteDomain ->
        listNoteDomain.map { mapper.mapDomainToApp(it) }
    }

//    fun fetchInitialData() {
//        viewModelScope.launch(baseDispatcher.io()) {
//            val query = firebase.noteCollectionRef().get().result
//            val listNoteCloud = query?.toObjects(NoteCloud::class.java)
//
//        }
//    }
}


