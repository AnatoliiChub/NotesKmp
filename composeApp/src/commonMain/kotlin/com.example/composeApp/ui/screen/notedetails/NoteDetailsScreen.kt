package com.example.composeApp.ui.screen.notedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composeApp.ui.backgroundColor
import noteskmp.composeapp.generated.resources.Res
import noteskmp.composeapp.generated.resources.add_note
import noteskmp.composeapp.generated.resources.description
import noteskmp.composeapp.generated.resources.note_editor
import noteskmp.composeapp.generated.resources.save
import noteskmp.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource
import org.koin.core.parameter.parametersOf

class NoteDetailsScreen(private val id: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<NoteDetailsViewModel>(parameters = { parametersOf(id) })
        val state = viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(Res.string.note_editor)) },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.add_note)
                            )
                        }
                    }
                )
            },
            backgroundColor = backgroundColor
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding).padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.value.title,
                    onValueChange = { viewModel.onTitleChange(it) },
                    label = { Text(stringResource(Res.string.title)) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                OutlinedTextField(
                    value = state.value.content,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    label = { Text(stringResource(Res.string.description)) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 7
                )
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { viewModel.saveNote { navigator.pop() } },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(Res.string.save))
                }
            }
        }
    }
}