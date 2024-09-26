package com.example.composeApp.data

import kotlinx.coroutines.flow.Flow


/**
 * Interface for managing notes in the repository.
 */
interface NoteRepository {

    /**
     * Retrieves a note by its unique identifier.
     * @param id The unique identifier of the note.
     * @return The note if found, otherwise null.
     */
    suspend fun getById(id: String): Note?

    /**
     * Stores a note in the repository.
     * @param note The note to be stored.
     */
    suspend fun storeNote(note: Note)

    /**
     * Retrieves all notes as a flow.
     * @return A flow emitting the list of all notes.
     */
    fun getAllNotes(): Flow<List<Note>>

    /**
     * Disposes of any resources held by the repository.
     */
    fun dispose()
}