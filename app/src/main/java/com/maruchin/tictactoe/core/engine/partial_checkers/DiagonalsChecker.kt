package com.maruchin.tictactoe.core.engine.partial_checkers

import com.maruchin.tictactoe.core.engine.BaseWinnerChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker

class DiagonalsChecker : BaseWinnerChecker() {

    override fun getArraysToCheck(board: Board): Array<Array<PlayerMarker>> {
        return arrayOf(
            getFirstDiagonal(board),
            getSecondDiagonal(board)
        )
    }

    private fun getFirstDiagonal(board: Board): Array<PlayerMarker> {
        val diagonal = Array(board.size) { PlayerMarker.NONE }
        var rowNum = 0
        var colNum = 0
        while (rowNum < board.size) {
            diagonal[rowNum] = board.fields[rowNum][colNum]
            rowNum++
            colNum++
        }
        return diagonal
    }

    private fun getSecondDiagonal(board: Board): Array<PlayerMarker> {
        val diagonal = Array(board.size) { PlayerMarker.NONE }
        var rowNum = 0
        var colNum = board.size - 1
        while (rowNum < board.size) {
            diagonal[rowNum] = board.fields[rowNum][colNum]
            rowNum++
            colNum--
        }
        return diagonal
    }
}