package com.example.notes.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.notes.di.ToDispatch
import com.example.notes.domain.models.Result
import com.example.notes.domain.usecases.FetchNotesUseCase
import com.example.notes.model.MapperNoteApp
import com.example.notes.model.NoteApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    fetchNotesUseCase: FetchNotesUseCase,
    private val baseDispatcher: ToDispatch,
    private val mapper: MapperNoteApp,
) : ViewModel() {

    private var mAllNotes = MutableLiveData<List<NoteApp>>()
    val allNotes: LiveData<List<NoteApp>>
        get() = mAllNotes

    init {
        when (val result = fetchNotesUseCase.execute()) {
            is Result.Success -> {
                Log.d("AAA", "Success:")
                mAllNotes = result.notesDomain.map { listNoteDomain ->
                    listNoteDomain.map { mapper.mapDomainToApp(it) }
                } as MutableLiveData<List<NoteApp>>
            }
            is Result.Fail -> {
                Log.d("AAA", "exception: ${result.error.cause}")
            }
        }
    }
}


