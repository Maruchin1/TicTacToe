package com.maruchin.tictactoe.presentation

import com.maruchin.tictactoe.presentation.game.GameViewModel
import com.maruchin.tictactoe.presentation.game.MarkersToResMapper
import com.maruchin.tictactoe.presentation.game.PositionToCoordinatesMapper
import com.maruchin.tictactoe.presentation.menu.DuoViewModel
import com.maruchin.tictactoe.presentation.menu.SoloViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        MarkersToResMapper()
    }
    single {
        PositionToCoordinatesMapper()
    }
    viewModel {
        GameViewModel(
            playersSession = get(),
            markersToResMapper = get(),
            positionToCoordinatesMapper = get()
        )
    }
    viewModel {
        SoloViewModel()
    }
    viewModel {
        DuoViewModel()
    }
}