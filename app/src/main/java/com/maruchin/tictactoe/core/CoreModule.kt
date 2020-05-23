package com.maruchin.tictactoe.core

import com.maruchin.tictactoe.core.engine.WinningMoveChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.ColumnChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.FirstDiagonalChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.RowChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.SecondDiagonalChecker
import org.koin.dsl.module

val coreModule = module {
    single {
        RowChecker()
    }
    single {
        ColumnChecker()
    }
    single {
        FirstDiagonalChecker()
    }
    single {
        SecondDiagonalChecker()
    }
    single {
        WinningMoveChecker(
            rowChecker = get(),
            columnChecker = get(),
            firstDiagonalChecker = get(),
            secondDiagonalChecker = get()
        )
    }
    single {
        PlayersSession(
            winningMoveChecker = get(),
            playersScoreRepo = get()
        )
    }
}