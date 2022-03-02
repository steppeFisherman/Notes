package com.example.notes.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.di.ToDispatch
import com.example.notes.domain.usecases.DeleteNoteUseCase
import com.example.notes.model.MapperNoteApp
import com.example.notes.model.NoteApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteFragmentViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val baseDispatcher: ToDispatch,
    private val mapper: MapperNoteApp
) :
    ViewModel() {

    fun delete(noteApp: NoteApp, onSuccess: () -> Unit) {
        viewModelScope.launch(baseDispatcher.io()) {
            deleteNoteUseCase.execute(mapper.mapAppToDomain(noteApp = noteApp)) {
                viewModelScope.launch(baseDispatcher.ui()) {
                    onSuccess()
                }
            }
        }
    }
}