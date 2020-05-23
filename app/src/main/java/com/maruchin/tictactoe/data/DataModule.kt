package com.maruchin.tictactoe.data

import com.maruchin.tictactoe.core.repository.PlayersScoreRepo
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<PlayersScoreRepo> {
        LocalPlayerScoreRepo(
            context = androidApplication()
        )
    }
}