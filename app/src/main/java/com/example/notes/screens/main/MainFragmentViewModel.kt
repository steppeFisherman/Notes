package com.example.notes.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.notes.di.ToDispatch
import com.example.notes.domain.models.Result
import com.example.notes.domain.usecases.FetchNotesUseCase
import com.example.notes.domain.models.ErrorType
import com.example.notes.model.MapperNoteApp
import com.example.notes.model.NoteApp
import com.google.firebase.FirebaseApiNotAvailableException
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    fetchNotesUseCase: FetchNotesUseCase,
    private val baseDispatcher: ToDispatch,
    private val mapper: MapperNoteApp,
) : ViewModel() {

    private var mAllNotes = MutableLiveData<List<NoteApp>>()
    private var mError = MutableLiveData<ErrorType>()
    val allNotes: LiveData<List<NoteApp>>
        get() = mAllNotes
    val error: LiveData<ErrorType>
        get() = mError

    init {
        when (val result = fetchNotesUseCase.execute()) {
            is Result.Success -> {
                mAllNotes = result.notesDomain.map { listNoteDomain ->
                    listNoteDomain.map { mapper.mapDomainToApp(it) }
                } as MutableLiveData<List<NoteApp>>
            }
            is Result.Fail -> mError.value = result.errorType
        }
    }
}


