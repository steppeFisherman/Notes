package com.example.notes.data.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.data.storage.models.NoteData

@Dao
interface AppRoomDao {

    @Query("SELECT * from notes_table")
    fun getAllNotes(): LiveData<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteData)

    @Delete
    suspend fun delete(note: NoteData)
}