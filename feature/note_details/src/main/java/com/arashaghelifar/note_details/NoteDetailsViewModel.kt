package com.arashaghelifar.note_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arashaghelifar.note.model.InvalidNoteException
import com.arashaghelifar.note.model.Note
import com.arashaghelifar.note.usecase.AddNoteUseCase
import com.arashaghelifar.note.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalTime
import org.threeten.bp.ZonedDateTime.now
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Title"))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(hint = "Note"))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteReminder = mutableStateOf<Long?>(null)
    val noteReminder: State<Long?> = _noteReminder

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    getNoteUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteDetailsEvent) {
        when (event) {
            is NoteDetailsEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }

            is NoteDetailsEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }

            is NoteDetailsEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }

            is NoteDetailsEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }

            is NoteDetailsEvent.SaveNoteDetails -> {
                viewModelScope.launch {
                    try {
                        val noteId = addNoteUseCase(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                reminder = noteReminder.value,
                                id = currentNoteId ?: 0
                            )
                        )

                        _eventFlow.emit(UiEvent.SaveNote(noteId))
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    fun getReminderOptions(): List<ReminderOption> {

        val now = now()

        val laterToday = now.plusMinutes(30)
        val laterTodayTimestamp = laterToday.toInstant().toEpochMilli()

        val tomorrow = now().plusDays(1).with(LocalTime.of(8, 0))
        val tomorrowMorningTimestamp = tomorrow.toInstant().toEpochMilli()

        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
        val laterTodayFormatted = laterToday.toLocalTime().format(timeFormatter)
        val tomorrowMorningFormatted = tomorrow.toLocalTime().format(timeFormatter)


        return listOf(
            ReminderOption(
                icon = R.drawable.svg_clock,
                title = "Later Today",
                time = laterTodayTimestamp,
                timeFormated = laterTodayFormatted
            ),
            ReminderOption(
                icon = R.drawable.svg_clock,
                title = "Tomorrow Morning",
                time = tomorrowMorningTimestamp,
                timeFormated = tomorrowMorningFormatted
            ),
            ReminderOption(
                icon = R.drawable.svg_calendar,
                title = "Pick a Date",
                actionIcon = R.drawable.svg_add
            ),

            )
    }

    fun setReminder(reminderOption: Long) {
        _noteReminder.value = reminderOption
    }

}