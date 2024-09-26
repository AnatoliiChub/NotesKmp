package com.example.composeApp.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun getById(id: String): Note?

    suspend fun storeNote(note: Note)

    fun getAllNotes(): Flow<List<Note>>

    fun dispose()
}