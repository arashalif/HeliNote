package com.arashaghelifar.note.usecase

import com.arashaghelifar.note.model.Note
import com.arashaghelifar.note.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NotesRepository) {

    operator fun invoke(query: String): Flow<List<Note>> {
        return repository.fetchAllNotes(query)
    }
}