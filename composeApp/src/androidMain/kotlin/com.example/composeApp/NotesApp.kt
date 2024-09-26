package com.example.composeApp

import android.app.Application
import com.example.composeApp.di.dataModule
import com.example.composeApp.di.viewModelModule
import org.koin.core.context.startKoin

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(viewModelModule, dataModule)
        }
    }
}