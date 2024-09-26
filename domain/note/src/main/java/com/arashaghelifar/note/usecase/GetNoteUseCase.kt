package com.arashaghelifar.note.usecase

import com.arashaghelifar.note.model.Note
import com.arashaghelifar.note.repository.NotesRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}