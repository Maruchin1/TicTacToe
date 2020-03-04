package com.maruchin.tictactoe

import android.app.Application
import com.maruchin.tictactoe.core.coreModule
import com.maruchin.tictactoe.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(coreModule + presentationModule)
        }
    }
}