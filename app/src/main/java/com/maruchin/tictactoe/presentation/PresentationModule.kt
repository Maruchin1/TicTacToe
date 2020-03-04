package com.maruchin.tictactoe.presentation

import com.maruchin.tictactoe.presentation.board.BoardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        BoardViewModel(
            gameService = get()
        )
    }
}