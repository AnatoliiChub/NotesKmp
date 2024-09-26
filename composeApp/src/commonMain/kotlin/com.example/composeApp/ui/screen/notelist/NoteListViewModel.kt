package com.example.composeApp.ui.screen.notelist

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.composeApp.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class NoteListViewModel(private val noteRepo: NoteRepository) : ScreenModel {

    val state = noteRepo.getAllNotes().flowOn(Dispatchers.Default)
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    override fun onDispose() {
        super.onDispose()
        noteRepo.dispose()
    }

}