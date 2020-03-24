package com.maruchin.tictactoe.core

import com.maruchin.tictactoe.core.engine2.WinningMoveChecker
import com.maruchin.tictactoe.core.engine2.partial_checkers.ColumnChecker
import com.maruchin.tictactoe.core.engine2.partial_checkers.FirstDiagonalChecker
import com.maruchin.tictactoe.core.engine2.partial_checkers.RowChecker
import com.maruchin.tictactoe.core.engine2.partial_checkers.SecondDiagonalChecker
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
        GameService(
            winningMoveChecker = get()
        )
    }
}