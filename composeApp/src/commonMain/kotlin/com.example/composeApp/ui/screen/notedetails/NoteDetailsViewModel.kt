package com.example.composeApp.ui.screen.notedetails

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.composeApp.data.Note
import com.example.composeApp.data.NoteRepository
import com.example.composeApp.util.DateFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class NoteDetailsViewModel(
    noteId: String,
    private val noteRepo: NoteRepository,
    private val dateFormatter: DateFormatter,
) :
    ScreenModel {
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

    init {
        screenModelScope.launch(Dispatchers.Default) {
            if (noteId.isNotEmpty()) {
                val note = noteRepo.getById(id.value) ?: Note()
                title.value = note.title
                description.value = note.content
                date.value = note.date
            }
        }
    }

    fun onTitleChange(value: String) {
        title.value = value
    }

    fun onDescriptionChange(value: String) {
        description.value = value
    }

    fun saveNote(onSaved: () -> Unit) {
        screenModelScope.launch(Dispatchers.Default) {
            noteRepo.storeNote(
                Note(
                    id = id.value,
                    title = title.value,
                    content = description.value,
                    date = dateFormatter.format(Clock.System.now())
                )
            )
            onSaved()
        }
    }

    override fun onDispose() {
        super.onDispose()
        noteRepo.dispose()
    }
}