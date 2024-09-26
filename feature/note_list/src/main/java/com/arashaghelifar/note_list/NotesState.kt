package com.arashaghelifar.note_list

import com.arashaghelifar.note.model.Note

data class NotesState(
    val notesList: List<Note> = emptyList(),
    val notesGrid: Pair<List<Note>, List<Note>> = Pair(emptyList(), emptyList()),
    val listType: ListType = ListType.ListNotes,
    val query: String = ""
)

sealed class ListType {
    data object ListNotes : ListType()
    data object GridNotes : ListType()
}

fun ListType.toggle(): ListType {
    return when (this) {
        ListType.ListNotes -> ListType.GridNotes
        ListType.GridNotes -> ListType.ListNotes
    }
}

fun List<Note>.toNoteGrid(): Pair<List<Note>, List<Note>> {
    val column1Notes = this.filterIndexed { index, _ -> index % 2 == 0 }
    val column2Notes = this.filterIndexed { index, _ -> index % 2 == 1 }
    return Pair(column1Notes, column2Notes)
}