package com.arashaghelifar.repository

import com.arashaghelifar.note.model.Note
import com.arashaghelifar.note.repository.NotesRepository
import com.arashaghelifar.repository.data_source.NotesLocalDataSource
import com.arashaghelifar.repository.model.toNote
import com.arashaghelifar.repository.model.toNoteDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class DefaultNotesRepository @Inject constructor(private val localDataSource: NotesLocalDataSource) :
    NotesRepository {

    override fun fetchAllNotes(query:String): Flow<List<Note>> =
        localDataSource.fetchAllNotes(query).map { listNote ->
            listNote.map { note ->
                note.toNote()
            }
        }

    override suspend fun getNoteById(id: Int): Note? = localDataSource.getNoteById(id)?.toNote()

    override suspend fun insertNote(note: Note):Int {
        return localDataSource.insertNote(note.toNoteDTO())
    }

    override suspend fun deleteNote(note: Note) {
        localDataSource.deleteNote(note.toNoteDTO())
    }

}