package com.example.notes.domain.models

data class NoteDomain(
    val id: Int = 0,
    val firebaseId: String = "",
    val name: String = "",
    val text: String = "",
    var performDate: Long = 0,
    var performed: Boolean = false
)
