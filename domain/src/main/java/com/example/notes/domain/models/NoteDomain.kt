package com.example.notes.domain.models

import java.io.Serializable

data class NoteDomain(
    val id: Int = 0,
    val name: String = "",
    val text: String = "",
) : Serializable