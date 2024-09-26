package com.arashaghelifar.note.usecase

import com.arashaghelifar.note.model.InvalidNoteException
import com.arashaghelifar.note.model.Note
import com.arashaghelifar.note.repository.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note):Int {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        return repository.insertNote(note)
    }
}