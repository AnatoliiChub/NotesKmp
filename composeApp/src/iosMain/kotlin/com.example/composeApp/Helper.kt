package com.example.composeApp

import com.example.composeApp.di.dataModule
import com.example.composeApp.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(viewModelModule, dataModule)
    }
}