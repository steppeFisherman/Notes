package com.example.notes.screens.add_new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.di.ToDispatch
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.usecases.AddNewNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddNewNoteViewModel @Inject constructor(
    private val addNewNoteUseCase: AddNewNoteUseCase,
    private val baseDispatcher: ToDispatch
) :
    ViewModel() {

    fun insert(note: NoteDomain, onSuccess: () -> Unit) {
        viewModelScope.launch(baseDispatcher.io()) {
            val currentDateTime = Calendar.getInstance().time.time
            note.performDate = currentDateTime
            addNewNoteUseCase.execute(note) {
                viewModelScope.launch(baseDispatcher.ui()) {
                    onSuccess()
                }
            }
        }
    }
}




