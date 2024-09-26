package com.arashaghelifar.note_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arashaghelifar.note_details.components.SelectTimeModal
import com.arashaghelifar.note_details.components.ReminderOptionsBottomSheet
import com.arashaghelifar.note_details.components.TopBar
import com.arashaghelifar.note_details.components.TransparentHintTextField
import com.arashaghelifar.note_details.reminder.scheduleReminder
import com.arashaghelifar.ui.theme.ColorSecondaryVariant20
import com.arashaghelifar.ui.utils.mapTimestampToDateText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NoteDetailsScreen(
    navController: NavController,
    viewModel: NoteDetailsViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val reminderState = viewModel.noteReminder.value
    val snackbarHostState = remember { SnackbarHostState() }
    var showReminderOptions by rememberSaveable { mutableStateOf(false) }
    var showSelectTime by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is UiEvent.SaveNote -> {
                    reminderState?.let {
                        scheduleReminder(
                            context = context,
                            noteId = event.noteId,
                            reminderTime = reminderState
                        )
                    }

                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
                onClick = {
                    viewModel.onEvent(NoteDetailsEvent.SaveNoteDetails)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.svg_save),
                    contentDescription = "Save note"
                )
            }
        }
    ) { padding ->

        if (showReminderOptions) {
            ReminderOptionsBottomSheet(
                options = viewModel.getReminderOptions(),
                onDismiss = {
                    showReminderOptions = false
                },
                onClicked = { reminderOption ->
                    showReminderOptions = false
                    if (reminderOption.time != null) {
                        viewModel.setReminder(reminderOption.time)
                    } else {
                        showSelectTime = true
                    }
                }
            )
        }

        if (showSelectTime) {
            SelectTimeModal(
                onDismiss = { showSelectTime = false },
                onSaveReminder = { d ->
                    viewModel.setReminder(d)
                    showSelectTime = false
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            TopBar(
                modifier = Modifier,
                onBackedPres = {
                    navController.popBackStack()
                },
                onReminderPressed = {
                    showReminderOptions = true
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (reminderState != null) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.svg_timer),
                            contentDescription = "Timer Icon"
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = reminderState.mapTimestampToDateText(),
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
            }


            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(NoteDetailsEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteDetailsEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = ColorSecondaryVariant20,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(NoteDetailsEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(NoteDetailsEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                ),
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}