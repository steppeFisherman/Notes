package com.example.notes.di

import android.content.Context
import com.example.notes.data.repository.MapperNoteDB
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.data.storage.firebase.FirebaseInstance
import com.example.notes.data.storage.room.AppRoomDao
import com.example.notes.data.storage.room.AppRoomDatabase
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.utils.ConnectionLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context): AppRoomDao =
        AppRoomDatabase.getInstance(context = context).getAppRoomDao()

    @Provides
    @Singleton
    fun provideRepository(appRoomDao: AppRoomDao): NoteRepository =
        NoteRepositoryImpl(
            appRoomDao = appRoomDao,
            MapperNoteDB.Base(),
            FirebaseInstance.Base()
        )

    @Provides
    @Singleton
    fun provideConnectionLiveData(@ApplicationContext context: Context): ConnectionLiveData =
        ConnectionLiveData(context = context)
}