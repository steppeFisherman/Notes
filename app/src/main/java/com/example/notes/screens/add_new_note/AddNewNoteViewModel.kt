package com.example.notes.screens.add_new_note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.models.NoteDomain
import com.example.notes.domain.usecases.AddNewNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(private val addNewNoteUseCase: AddNewNoteUseCase) : ViewModel() {

//    fun insert(note: AppNote, onSuccess: () -> Unit) =
//        viewModelScope.launch(Dispatchers.IO) {
//            REPOSITORY.insert(note) {
//                viewModelScope.launch(Dispatchers.Main) {
//                    onSuccess()
//                }
//            }
//        }

//    private var resultLiveMutable = MutableLiveData<NoteDomain>()
//    val resultLive: LiveData<NoteDomain> = resultLiveMutable
//
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