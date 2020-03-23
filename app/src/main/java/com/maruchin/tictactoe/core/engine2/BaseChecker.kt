package com.maruchin.tictactoe.core.engine2

import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates

abstract class BaseChecker(
    private val board: Board,
    private val winningNum: Int,
    private val moveCoordinates: Coordinates
) {
    private val movingMarker = board.getForCoordinates(moveCoordinates)
    private var sameMarkers = 1

    fun check(): Boolean {
        checkInDirection(moveCoordinates, Direction.NEGATIVE)
        checkInDirection(moveCoordinates, Direction.POSITIVE)
        return sameMarkers >= winningNum
    }

    private fun checkInDirection(currCoordinates: Coordinates, direction: Direction) {
        val nextCoordinates = getNextCoordinates(currCoordinates, direction)
        if (board.containsCoordinates(nextCoordinates)) {
            val nextMarker = board.getForCoordinates(nextCoordinates)
            if (nextMarker == movingMarker) {
                sameMarkers++
                return checkInDirection(nextCoordinates, direction)
            }
        }
    }

    private fun getNextCoordinates(
        currCoordinates: Coordinates,
        direction: Direction
    ): Coordinates {
        return when (direction) {
            Direction.NEGATIVE -> getNextNegativeCoordinates(currCoordinates)
            Direction.POSITIVE -> getNextPositiveCoordinates(currCoordinates)
        }
    }

    abstract fun getNextNegativeCoordinates(currCoordinates: Coordinates): Coordinates

    abstract fun getNextPositiveCoordinates(currCoordinates: Coordinates): Coordinates

    private enum class Direction {
        NEGATIVE, POSITIVE
    }
}