package com.maruchin.tictactoe.core.engine2

import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates

class ColumnChecker(
    board: Board,
    winningNum: Int,
    moveCoordinates: Coordinates
) : BaseChecker(board, winningNum, moveCoordinates) {

    override fun getNextNegativeCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            column = currCoordinates.row - 1
        )
    }

    override fun getNextPositiveCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            column = currCoordinates.row + 1
        )
    }
}