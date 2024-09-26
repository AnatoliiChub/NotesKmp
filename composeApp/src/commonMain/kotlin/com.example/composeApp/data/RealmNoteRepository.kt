package com.example.composeApp.data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealmNoteRepository(config: RealmConfiguration) : NoteRepository {

    private val realm = Realm.open(config)

    override suspend fun getById(id: String): Note =
        realm.copyFromRealm(realm.query(Note::class, "id = $0", id).first().find() ?: Note())


    override suspend fun storeNote(note: Note) {
        realm.writeBlocking {
            copyToRealm(note, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override fun getAllNotes(): Flow<List<Note>> = realm.query(Note::class)
        .asFlow()
        .map { realm.copyFromRealm(it.list) }

    override fun dispose() {
        realm.close()
    }
}