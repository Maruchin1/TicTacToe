package com.maruchin.tictactoe.core.engine2

import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.core.entities.PlayerMarker

abstract class OneAxisChecker {
    private lateinit var movingMarker: PlayerMarker
    private var sameMarkers: Int = 1

    fun check(
        board: Board,
        winningNum: Int,
        moveCoordinates: Coordinates
    ): Boolean {
        movingMarker = board.getForCoordinates(moveCoordinates)
        sameMarkers = 1
        checkInDirection(board, moveCoordinates, Direction.NEGATIVE)
        checkInDirection(board, moveCoordinates, Direction.POSITIVE)
        return sameMarkers >= winningNum
    }

    private fun checkInDirection(
        board: Board,
        currCoordinates: Coordinates,
        direction: Direction
    ) {
        val nextCoordinates = getNextCoordinates(currCoordinates, direction)
        if (board.containsCoordinates(nextCoordinates)) {
            val nextMarker = board.getForCoordinates(nextCoordinates)
            if (nextMarker == movingMarker) {
                sameMarkers++
                return checkInDirection(board, nextCoordinates, direction)
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