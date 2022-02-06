package com.example.notes.di

import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.usecases.AddNewNoteUseCase
import com.example.notes.domain.usecases.DeleteNoteUseCase
import com.example.notes.domain.usecases.FetchNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideAddNewNoteUseCase(noteRepository: NoteRepository) =
        AddNewNoteUseCase(noteRepository = noteRepository)

    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository) =
        DeleteNoteUseCase(noteRepository = noteRepository)

    @Provides
    fun provideFetchNotesUseCase(noteRepository: NoteRepository) =
        FetchNotesUseCase(noteRepository = noteRepository)

    @Provides
    fun provideDispatchers() = BaseDispatcher()
}