package com.arashaghelifar.note_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arashaghelifar.note_list.components.Notes
import com.arashaghelifar.note_list.components.TopBar
import com.arashaghelifar.ui.route.Screen
import com.arashaghelifar.ui.theme.ColorSecondaryVariant20


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
                onClick = {
                    navController.navigate(route = Screen.AddEditNoteScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {

            TopBar(
                searchQuery = viewModel.searchQuery.value,
                onSearchChanged = viewModel::onSearchQueryChanged,
                listType = state.listType,
                onListTypeChange = viewModel::onListTypeChanged,
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = ColorSecondaryVariant20,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Notes(notesList = state.notesList, notesGrid= state.notesGrid, listType = state.listType ) {
                navController.navigate(route = Screen.AddEditNoteScreen.route + "?noteId=${it.id}")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
