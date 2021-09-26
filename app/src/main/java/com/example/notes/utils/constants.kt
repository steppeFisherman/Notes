package com.example.notes.utils

import com.example.notes.MainActivity
import com.example.notes.data.storage.AppRoomDao
import com.example.notes.domain.repository.NoteRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: NoteRepository
lateinit var DAO: AppRoomDao
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"