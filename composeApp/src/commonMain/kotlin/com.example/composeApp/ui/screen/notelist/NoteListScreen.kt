package com.example.composeApp.ui.screen.notelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composeApp.ui.screen.notedetails.NoteDetailsScreen
import com.example.composeApp.ui.view.NoteListItem
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.add_note
import noteskmp.composeapp.generated.resources.notes
import org.jetbrains.compose.resources.stringResource


class NoteListScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinScreenModel<NoteListViewModel>()
        val notes = viewModel.state.collectAsState().value
        Scaffold(topBar = {
            TopAppBar(title = {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(stringResource( Res.string.notes))
                }
            })
        }, floatingActionButton = {
            FloatingActionButton(onClick = { navigator.push(NoteDetailsScreen("")) }) {
                Icon(Icons.Filled.Add, contentDescription = stringResource( Res.string.add_note))
            }
        }) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding).padding(horizontal = 8.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
            ) {
                items(notes.size, key = { notes[it].id }) { index ->
                    NoteListItem(note = notes[index], onClick = { navigator.push(NoteDetailsScreen(notes[index].id)) })
                }
            }
        }
    }
}