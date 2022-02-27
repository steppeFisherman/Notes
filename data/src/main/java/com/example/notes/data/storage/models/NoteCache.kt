package com.example.notes.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class NoteCache(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo
    val name: String = "",
    @ColumnInfo val text: String = "",
) : Serializable


