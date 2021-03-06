package com.example.notes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteApp(
    val id: Int = 0,
    val firebaseId: String = "",
    val name: String = "",
    var text: String = "",
    var performDate: String = "",
    var performed: Boolean = false
) : Parcelable