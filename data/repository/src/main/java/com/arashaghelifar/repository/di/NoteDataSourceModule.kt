package com.arashaghelifar.repository.di


import com.arashaghelifar.note.repository.NotesRepository
import com.arashaghelifar.repository.DefaultNotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NoteDataSourceModule {

    @Binds
    @Singleton
    fun bindNotesRepository(defaultNotesRepository: DefaultNotesRepository): NotesRepository

}