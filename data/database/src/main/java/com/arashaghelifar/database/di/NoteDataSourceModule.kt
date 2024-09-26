package com.arashaghelifar.database.di


import com.arashaghelifar.database.DefaultNotesLocalDataSource
import com.arashaghelifar.repository.data_source.NotesLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NoteDataSourceModule {

    @Binds
    abstract fun bindLocalDatasource(localDataSource: DefaultNotesLocalDataSource): NotesLocalDataSource
}