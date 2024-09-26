package com.example.composeApp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.composeApp.di.viewModelModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(viewModelModule)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "NotesKmp",
    ) {
        App()
    }
}