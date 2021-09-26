package com.example.notes.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.usecases.DeleteNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(private val deleteNoteUseCase: DeleteNoteUseCase)
    : ViewModel() {

    fun delete(note: NoteDomain, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.execute(note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}