package com.arashaghelifar.note.model

data class Note(
    val title: String,
    val content: String,
    val reminder: Long?,
    val id: Int,
)

class InvalidNoteException(message: String): Exception(message)