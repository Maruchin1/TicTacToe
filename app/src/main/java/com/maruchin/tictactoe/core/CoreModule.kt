package com.maruchin.tictactoe.core

import com.maruchin.tictactoe.core.engine.partial_checkers.ColumnsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.DiagonalsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.RowsChecker
import org.koin.dsl.module

val coreModule = module {
    single {
        RowsChecker()
    }
    single {
        ColumnsChecker()
    }
    single {
        DiagonalsChecker()
    }
    single {
        GameService(get())
    }
}