package com.example.notes.screens.add_new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.usecases.AddNewNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewNoteViewModel @Inject constructor(private val addNewNoteUseCase: AddNewNoteUseCase) :
    ViewModel() {

    fun insert(note: NoteDomain, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewNoteUseCase.execute(note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}