package com.maruchin.tictactoe.presentation

import com.maruchin.tictactoe.presentation.board.BoardViewModel
import com.maruchin.tictactoe.presentation.board.MarkersToResMapper
import com.maruchin.tictactoe.presentation.board.PositionToCoordinatesMapper
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
        BoardViewModel(
            gameService = get(),
            markersToResMapper = get(),
            positionToCoordinatesMapper = get()
        )
    }
}