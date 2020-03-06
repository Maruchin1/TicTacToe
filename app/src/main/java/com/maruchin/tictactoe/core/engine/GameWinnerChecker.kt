package com.maruchin.tictactoe.core.engine

import com.maruchin.tictactoe.core.engine.partial_checkers.ColumnsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.DiagonalsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.RowsChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker

class GameWinnerChecker(
    private val rowsChecker: RowsChecker,
    private val columnsChecker: ColumnsChecker,
    private val diagonalsChecker: DiagonalsChecker
) {

    fun check(board: Board, winningNum: Int): PlayerMarker {
        var winner = rowsChecker.check(board, winningNum)
        if (winner == PlayerMarker.NONE) {
            winner = columnsChecker.check(board, winningNum)
            if (winner == PlayerMarker.NONE) {
                winner = diagonalsChecker.check(board, winningNum)
            }
        }
        return winner
    }
}