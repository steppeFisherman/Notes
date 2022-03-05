package com.example.notes.screens.add_new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.di.ToDispatch
import com.example.notes.domain.usecases.AddNewNoteUseCase
import com.example.notes.model.MapperNoteApp
import com.example.notes.model.NoteApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewNoteViewModel @Inject constructor(
    private val addNewNoteUseCase: AddNewNoteUseCase,
    private val baseDispatcher: ToDispatch,
    private val mapper: MapperNoteApp
) :
    ViewModel() {

    fun insert(noteApp: NoteApp, onSuccess: () -> Unit) {
        viewModelScope.launch(baseDispatcher.io()) {
            addNewNoteUseCase.execute(mapper.mapAppToDomain(noteApp = noteApp)) {
                viewModelScope.launch(baseDispatcher.ui()) {
                    onSuccess()
                }
            }
        }
    }
}




