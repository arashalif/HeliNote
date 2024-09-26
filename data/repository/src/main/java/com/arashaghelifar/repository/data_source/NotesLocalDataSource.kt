package com.arashaghelifar.repository.data_source

import com.arashaghelifar.repository.model.NoteDTO
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {

    fun fetchAllNotes(query:String): Flow<List<NoteDTO>>

    suspend fun getNoteById(id: Int): NoteDTO?

    suspend fun insertNote(noteDTO: NoteDTO):Int

    suspend fun deleteNote(noteDTO: NoteDTO)
}