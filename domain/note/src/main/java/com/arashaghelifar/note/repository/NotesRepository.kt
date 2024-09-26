package com.arashaghelifar.note.repository

import com.arashaghelifar.note.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun fetchAllNotes(query:String): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note):Int

    suspend fun deleteNote(note: Note)
}