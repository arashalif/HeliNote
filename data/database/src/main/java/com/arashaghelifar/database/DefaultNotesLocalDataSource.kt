package com.arashaghelifar.database

import com.arashaghelifar.repository.data_source.NotesLocalDataSource
import com.arashaghelifar.database.dao.NoteDAO
import com.arashaghelifar.database.entity.mapToDTO
import com.arashaghelifar.database.entity.mapToEntity
import com.arashaghelifar.repository.model.NoteDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultNotesLocalDataSource @Inject internal constructor(private val noteDAO: NoteDAO) :
    NotesLocalDataSource {

    override fun fetchAllNotes(query:String): Flow<List<NoteDTO>> =
        noteDAO.getNotes(query).map { listNote->
            listNote.map {
                it.mapToDTO()
            }
        }

    override suspend fun getNoteById(id: Int): NoteDTO? =
        noteDAO.getNoteById(id = id)?.mapToDTO()

    override suspend fun insertNote(noteDTO: NoteDTO):Int {
        return noteDAO.insertNote(noteEntity = noteDTO.mapToEntity()).toInt()?:-1
    }

    override suspend fun deleteNote(noteDTO: NoteDTO) {
        noteDAO.deleteNote(noteEntity = noteDTO.mapToEntity())
    }
}