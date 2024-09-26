package com.example.composeApp.ui.screen.notelist

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.composeApp.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class NoteListViewModel : ScreenModel {

    val state = flow {
        emit(listOf(
            Note(
                "1",
                "First note",
                "This is the first note",
                "2021-01-01"
            ),
            Note(
                "2",
                "Second note",
                "This is the second note",
                "2021-01-02"
            ),
            Note(
                "3",
                "Third note",
                "This is the third note",
                "2021-01-03"
            )
        ))
    }.flowOn(Dispatchers.Default)
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

}