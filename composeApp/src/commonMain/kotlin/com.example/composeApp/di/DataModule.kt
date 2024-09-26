package com.example.composeApp.di

import com.example.composeApp.data.Note
import com.example.composeApp.data.NoteRepository
import com.example.composeApp.data.RealmNoteRepository
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val dataModule = module {
    single {
        RealmConfiguration.Builder(schema = setOf(Note::class))
            .deleteRealmIfMigrationNeeded()
            .build()
    }
    factory<NoteRepository> { RealmNoteRepository(get()) }
}