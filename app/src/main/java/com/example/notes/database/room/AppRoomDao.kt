package com.example.notes.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.AppNote

@Dao
interface AppRoomDao {

    @Query("SELECT * from notes_table")
    fun getAllNotes(): LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: AppNote)

    @Delete
    suspend fun delete(note: AppNote)
}