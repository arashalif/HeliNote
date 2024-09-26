package com.arashaghelifar.note_details

import androidx.annotation.DrawableRes

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    data class SaveNote(val noteId: Int) : UiEvent()
}

data class ReminderOption(
    @DrawableRes val icon: Int,
    val title: String,
    val time: Long? = null,
    val timeFormated: String? = null,
    @DrawableRes val actionIcon: Int? = null
)