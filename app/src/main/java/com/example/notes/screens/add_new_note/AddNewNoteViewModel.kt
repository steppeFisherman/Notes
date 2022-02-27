package com.example.notes.screens.add_new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.di.BaseDispatcher
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.usecases.AddNewNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class AddNewNoteViewModel @Inject constructor(
    private val addNewNoteUseCase: AddNewNoteUseCase,
    private val baseDispatcher: BaseDispatcher
) :
    ViewModel() {

    fun insert(note: NoteDomain, onSuccess: () -> Unit) {
        viewModelScope.launch(baseDispatcher.io()) {
            addNewNoteUseCase.execute(note) {
                viewModelScope.launch(baseDispatcher.ui()) {
                    onSuccess()
                }
            }
        }
    }
}




