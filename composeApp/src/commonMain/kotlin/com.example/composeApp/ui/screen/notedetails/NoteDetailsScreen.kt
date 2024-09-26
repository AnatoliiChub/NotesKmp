package com.example.composeApp.ui.screen.notedetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class NoteDetailsScreen : Screen {

    @Composable
    override fun Content() {
        Text(modifier = Modifier.fillMaxSize(), text = "Note Details")
    }
}