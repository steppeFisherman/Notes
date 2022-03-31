package com.example.notes.data.storage.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.data.storage.models.NoteCache

@Dao
interface AppRoomDao {

    @Query("SELECT * from notes_table ORDER BY performDate Desc")
    fun getAllNotes(): LiveData<List<NoteCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(noteList: List<NoteCache>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteCache)

    @Delete
    suspend fun delete(note: NoteCache)
}