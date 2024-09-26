package com.example.composeApp.ui.screen.notedetails

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.composeApp.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class NoteDetailsViewModel(noteId: String) : ScreenModel {
    private val title = MutableStateFlow("")
    private val description = MutableStateFlow("")
    private val date = MutableStateFlow("")
    @OptIn(ExperimentalUuidApi::class)
    private val id = MutableStateFlow(noteId.ifEmpty { Uuid.random().toString() })

    val state: StateFlow<Note> = combine(title, description, date, id) { title, description, date, id ->
        Note(
            id = id,
            title = title,
            content = description,
            date = date
        )
    }.flowOn(Dispatchers.Default)
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5000),
            Note()
        )


    fun onTitleChange(value: String) {
        title.value = value
    }

    fun onDescriptionChange(value: String) {
        description.value = value
    }

    fun saveNote(onSaved: () -> Unit) {

        onSaved()
    }
}