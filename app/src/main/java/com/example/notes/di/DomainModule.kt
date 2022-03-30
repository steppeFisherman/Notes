package com.example.notes.di

import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.usecases.AddNewNoteUseCase
import com.example.notes.domain.usecases.DeleteNoteUseCase
import com.example.notes.domain.usecases.FetchNotesUseCase
import com.example.notes.model.MapperNoteApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideAddNewNoteUseCase(noteRepository: NoteRepository): AddNewNoteUseCase =
        AddNewNoteUseCase.Base(noteRepository = noteRepository)

    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase =
        DeleteNoteUseCase.Base(noteRepository = noteRepository)

    @Provides
    fun provideFetchNotesUseCase(noteRepository: NoteRepository): FetchNotesUseCase =
        FetchNotesUseCase.Base(noteRepository = noteRepository)

    @Provides
    fun provideMapperNoteApp(): MapperNoteApp =
        MapperNoteApp.Base()

    @Provides
    fun provideDispatchers(): ToDispatch = ToDispatch.Base()
}