package com.example.notes.model

import java.io.Serializable


data class AppNote(
    val id: Int = 0,
    val name: String = "",
    val text: String = "",
) : Serializable
