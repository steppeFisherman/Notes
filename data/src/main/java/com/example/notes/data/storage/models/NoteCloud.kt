package com.example.notes.data.storage.models

data class NoteCloud(
    val id: Int = 0,
    val firebaseId: String = "",
    val name: String = "",
    val text: String = "",
    val performDate: Long = 0,
    var performed: Boolean = false
)
