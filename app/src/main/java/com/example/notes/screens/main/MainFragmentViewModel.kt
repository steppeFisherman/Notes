package com.example.notes.screens.main

import androidx.lifecycle.ViewModel
import com.example.notes.utils.REPOSITORY

class MainFragmentViewModel : ViewModel() {
    val allNotes = REPOSITORY.allNotes
}