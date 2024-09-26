package com.arashaghelifar.note_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arashaghelifar.note_details.R
import com.arashaghelifar.ui.utils.convertToTimestamp
import com.arashaghelifar.ui.theme.ColorSecondaryVariant20
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTimeModal(
    onDismiss: () -> Unit,
    onSaveReminder: (Long) -> Unit
) {
    val currentTime = Calendar.getInstance()

    val dateFormatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormatter.format(currentTime.time)) }

    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    var selectedTime by remember { mutableStateOf(timeFormatter.format(currentTime.time)) }


    var datePicker by remember { mutableStateOf(false) }
    var dialPicker by remember { mutableStateOf(false) }



    if (datePicker) {
        DatePickerModal(
            onDateSelected = { d ->
                if (d != null) {
                    selectedDate = dateFormatter.format(Date(d))
                } else {
                    datePicker = false
                }
            },
            onDismiss = { datePicker = false }
        )
    }

    if (dialPicker) {
        TimePickerModal(
            onConfirm = {
                selectedTime = "${it.hour}:${it.minute}"
                dialPicker = false
            },
            onDismiss = { dialPicker = false }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onSaveReminder("$selectedDate $selectedTime".convertToTimestamp())
                }
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Add reminder",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
                )

                Spacer(modifier = Modifier.height(24.dp))

                DropdownField(
                    value = selectedDate
                ) {
                    datePicker = true
                }

                DropdownField(
                    value = selectedTime,
                ) {
                    dialPicker = true
                }
            }
        },
        containerColor = Color.White
    )
}

@Composable
fun DropdownField(
    value: String,
    onClicked: () -> Unit,
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClicked() }
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = value, style = TextStyle(fontSize = 12.sp))
            Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = ColorSecondaryVariant20,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerModal(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    TimePickerDialog(
        onDismiss = { onDismiss() },
        onConfirm = { onConfirm(timePickerState) }
    ) {
        TimePicker(
            state = timePickerState,
        )
    }
}

@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("OK")
            }
        },
        text = { content() }
    )
}

