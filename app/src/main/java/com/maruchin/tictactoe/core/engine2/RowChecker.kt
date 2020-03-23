package com.maruchin.tictactoe.core.engine2

import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates

class RowChecker(
    board: Board,
    winningNum: Int,
    moveCoordinates: Coordinates
) : BaseChecker(board, winningNum, moveCoordinates) {

    override fun getNextNegativeCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            row = currCoordinates.column - 1
        )
    }

    override fun getNextPositiveCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            row = currCoordinates.column + 1
        )
    }
}