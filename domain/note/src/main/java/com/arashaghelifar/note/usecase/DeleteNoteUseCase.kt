package com.arashaghelifar.note.usecase

import com.arashaghelifar.note.model.Note
import com.arashaghelifar.note.repository.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NotesRepository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}