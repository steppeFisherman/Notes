package com.example.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.database.room.AppRoomDatabase
import com.example.notes.database.room.AppRoomRepository
import com.example.notes.utils.REPOSITORY
import com.example.notes.utils.TYPE_ROOM

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}