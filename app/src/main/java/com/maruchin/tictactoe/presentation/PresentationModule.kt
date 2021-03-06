package com.maruchin.tictactoe.presentation

import com.maruchin.tictactoe.presentation.game.GameViewModel
import com.maruchin.tictactoe.presentation.game.PositionToCoordinatesMapper
import com.maruchin.tictactoe.presentation.menu.DuoViewModel
import com.maruchin.tictactoe.presentation.menu.SoloViewModel
import com.maruchin.tictactoe.presentation.scores.ScoresViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        PositionToCoordinatesMapper()
    }
    viewModel {
        GameViewModel(
            playersSession = get(),
            positionToCoordinatesMapper = get()
        )
    }
    viewModel {
        SoloViewModel()
    }
    viewModel {
        DuoViewModel()
    }
    viewModel {
        ScoresViewModel(
            scoreRepo = get()
        )
    }
}