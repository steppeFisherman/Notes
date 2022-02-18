package com.example.notes.data.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.data.storage.models.NoteDB

@Dao
interface AppRoomDao {

    @Query("SELECT * from notes_table")
    fun getAllNotes(): LiveData<List<NoteDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteDB)

    @Delete
    suspend fun delete(note: NoteDB)
}