package com.maruchin.tictactoe.presentation.board

import com.maruchin.tictactoe.core.entities.Coordinates

class PositionToCoordinatesMapper {

    fun map(position: Int, boardSize: Int): Coordinates {
        return Coordinates(
            row = calcRowNum(position, boardSize),
            column = calcColNum(position, boardSize)
        )
    }

    private fun calcRowNum(position: Int, boardSize: Int): Int {
        return position / boardSize
    }

    private fun calcColNum(position: Int, boardSize: Int): Int {
        return position % boardSize
    }
}