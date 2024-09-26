package com.arashaghelifar.database.di

import com.arashaghelifar.database.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal object DAOsModule {

    @Provides
    fun providesCharacterDao(database: NotesDatabase) = database.noteDAO
}