package com.arashaghelifar.note_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arashaghelifar.note.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    init {
        queryNotes(searchQuery.value)
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        queryNotes(query)
    }

    private fun queryNotes(query: String) {
        getNotesUseCase(query).onEach {
            _state.value = state.value.copy(
                notesList = it,
                notesGrid = it.toNoteGrid()
            )
        }.launchIn(viewModelScope)
    }

    fun onListTypeChanged(listType: ListType) {
        _state.value = state.value.copy(
            listType = listType
        )
    }

}