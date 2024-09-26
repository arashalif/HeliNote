package com.arashaghelifar.repository.model

import com.arashaghelifar.note.model.Note

data class NoteDTO(
    val title: String,
    val content: String,
    val reminder: Long?,
    val id: Int
)

internal fun NoteDTO.toNote() = Note(
    title = title,
    content = content,
    reminder = reminder,
    id = id
)

internal fun Note.toNoteDTO() = NoteDTO(
    title = title,
    content = content,
    reminder = reminder,
    id = id
)
