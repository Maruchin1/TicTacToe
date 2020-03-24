package com.maruchin.tictactoe.core.engine

import com.maruchin.tictactoe.core.engine.partial_checkers.ColumnChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.FirstDiagonalChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.RowChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.SecondDiagonalChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates

class WinningMoveChecker(
    private val rowChecker: RowChecker,
    private val columnChecker: ColumnChecker,
    private val firstDiagonalChecker: FirstDiagonalChecker,
    private val secondDiagonalChecker: SecondDiagonalChecker
) {

    fun check(board: Board, winningNum: Int, moveCoordinates: Coordinates): Boolean {
        val checkers = arrayOf(
            rowChecker,
            columnChecker,
            firstDiagonalChecker,
            secondDiagonalChecker
        )
        val checkResults = checkers.map { oneAxisChecker ->
            oneAxisChecker.check(board, winningNum, moveCoordinates)
        }
        return checkResults.any { it }
    }
}