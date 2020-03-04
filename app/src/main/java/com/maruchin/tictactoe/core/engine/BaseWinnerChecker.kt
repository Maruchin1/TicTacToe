package com.maruchin.tictactoe.core.engine

import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker

abstract class BaseWinnerChecker {

    fun check(board: Board): PlayerMarker {
        val arrays = getArraysToCheck(board)
        for (array in arrays) {
            val singleArrayResult = checkSingleArray(array)
            if (singleArrayResult != PlayerMarker.NONE) {
                return singleArrayResult
            }
        }
        return PlayerMarker.NONE
    }

    protected abstract fun getArraysToCheck(board: Board): Array<Array<PlayerMarker>>

    private fun checkSingleArray(array: Array<PlayerMarker>): PlayerMarker {
        val first = array[0]
        val allSameAsFirst = array.all {
            it == first
        }
        return if(allSameAsFirst) {
            first
        } else {
            PlayerMarker.NONE
        }
    }
}