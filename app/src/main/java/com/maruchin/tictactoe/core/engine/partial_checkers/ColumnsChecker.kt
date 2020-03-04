package com.maruchin.tictactoe.core.engine.partial_checkers

import com.maruchin.tictactoe.core.engine.BaseWinnerChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker

class ColumnsChecker : BaseWinnerChecker() {

    override fun getArraysToCheck(board: Board): Array<Array<PlayerMarker>> {
        val transposedBoard = getBoardTransposition(board)
        return transposedBoard.fields
    }

    private fun getBoardTransposition(board: Board): Board {
        val boardSize = board.fields.size
        val transposeBoard = Board(boardSize)
        for (rowNum in 0 until boardSize) {
            for (colNum in 0 until boardSize) {
                transposeBoard.fields[colNum][rowNum] = board.fields[rowNum][colNum]
            }
        }
        return transposeBoard
    }
}