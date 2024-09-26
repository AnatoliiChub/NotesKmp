package com.example.composeApp.di

import com.example.composeApp.ui.screen.notedetails.NoteDetailsViewModel
import com.example.composeApp.ui.screen.notelist.NoteListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { NoteListViewModel() }
    factory { params -> NoteDetailsViewModel(params.get()) }
}