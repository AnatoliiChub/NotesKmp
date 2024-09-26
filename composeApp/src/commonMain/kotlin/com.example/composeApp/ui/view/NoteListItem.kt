package com.example.composeApp.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeApp.data.Note

@Composable
fun NoteListItem(note: Note, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(2.dp).clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.height(128.dp).padding(8.dp)) {
            Row {
                Text(
                    note.title,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(note.date)
            }
            Spacer(Modifier.weight(1f))
            Text(
                note.content,
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(Modifier.weight(1f))
        }
    }
}