package com.maruchin.tictactoe.core.engine

import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker

abstract class BaseWinnerChecker {

    fun check(board: Board, winningNum: Int): PlayerMarker {
        val arrays = getArraysToCheck(board)
        for (array in arrays) {
            val singleArrayResult = checkSingleArray(array, winningNum)
            if (singleArrayResult != PlayerMarker.NONE) {
                return singleArrayResult
            }
        }
        return PlayerMarker.NONE
    }

    protected abstract fun getArraysToCheck(board: Board): Array<Array<PlayerMarker>>

    private fun checkSingleArray(array: Array<PlayerMarker>, winningNum: Int): PlayerMarker {
        val checker = SingleArrayChecker(array, winningNum)
        return checker.check()
    }
}