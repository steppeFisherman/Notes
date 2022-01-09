package com.example.notes.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.usecases.DeleteNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteFragmentViewModel @Inject constructor(private val deleteNoteUseCase: DeleteNoteUseCase) :
    ViewModel() {

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