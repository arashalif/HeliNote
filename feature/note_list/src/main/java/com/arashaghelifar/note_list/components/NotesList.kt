package com.arashaghelifar.note_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arashaghelifar.note.model.Note
import com.arashaghelifar.note_list.ListType
import com.arashaghelifar.note_list.R
import com.arashaghelifar.ui.theme.ColorSecondaryVariant
import com.arashaghelifar.ui.utils.mapTimestampToDateText

@Composable
internal fun Notes(
    notesList: List<Note>,
    notesGrid: Pair<List<Note>, List<Note>>,
    listType: ListType,
    onNoteClick: (Note) -> Unit
) {

    Column {
        Text(
            text = stringResource(R.string.recent_all_note),
            style = androidx.compose.ui.text.TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        when (listType) {
            ListType.ListNotes -> NotesList(notes = notesList, onNoteClick = onNoteClick)
            ListType.GridNotes -> GridNotes(notes = notesGrid, onNoteClick = onNoteClick)
        }
    }
}

@Composable
internal fun GridNotes(notes: Pair<List<Note>, List<Note>>, onNoteClick: (Note) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(notes.first) { note ->
                NoteCard(note = note, onNoteClick = onNoteClick)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(notes.second) { note ->
                NoteCard(note = note, onNoteClick = onNoteClick)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
internal fun NotesList(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(notes) { note ->
            NoteCard(note = note, onNoteClick = onNoteClick)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
internal fun NoteCard(note: Note, onNoteClick: (Note) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onNoteClick(note) },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )

            Text(
                text = note.content,
                color = ColorSecondaryVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            if (note.reminder != null) {
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
                            text = note.reminder!!.mapTimestampToDateText(),
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                }
            }

        }
    }
}
