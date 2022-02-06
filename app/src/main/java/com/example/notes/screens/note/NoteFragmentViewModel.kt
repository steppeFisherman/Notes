package com.example.notes.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.di.BaseDispatcher
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.usecases.DeleteNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteFragmentViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val baseDispatcher: BaseDispatcher
) :
    ViewModel() {

    fun delete(note: NoteDomain, onSuccess: () -> Unit) {
        viewModelScope.launch(baseDispatcher.io()) {
            deleteNoteUseCase.execute(note) {
                viewModelScope.launch(baseDispatcher.ui()) {
                    onSuccess()
                }
            }
        }
    }
}