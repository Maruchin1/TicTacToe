package com.maruchin.tictactoe.core.engine

import com.maruchin.tictactoe.core.entities.PlayerMarker

class SingleArrayChecker(
    private val array: Array<PlayerMarker>,
    private val winningNum: Int
) {
    private var sameMarkers = 1
    var prevMarker = array[0]
    var iterator = 1

    fun check(): PlayerMarker {
        while (isInBoundsAndNoWinnerYet()) {
            checkCurrMarker()
            iterator++
        }
        return getWinner()
    }

    private fun isInBoundsAndNoWinnerYet(): Boolean {
        return iterator < array.size && sameMarkers < winningNum
    }

    private fun checkCurrMarker() {
        val currMarker = array[iterator]
        if (currMarker != PlayerMarker.NONE) {
            if (currMarker == prevMarker) {
                sameMarkers++
            } else {
                sameMarkers = 1
                prevMarker = currMarker
            }
        }
    }

    private fun getWinner(): PlayerMarker {
        return if (sameMarkers == winningNum) {
            prevMarker
        } else {
            PlayerMarker.NONE
        }
    }
}