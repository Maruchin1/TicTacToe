package com.maruchin.tictactoe.core.engine2.partial_checkers

import com.maruchin.tictactoe.core.engine2.OneAxisChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates

class SecondDiagonalChecker : OneAxisChecker() {

    override fun getNextNegativeCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            row = currCoordinates.row + 1,
            column = currCoordinates.column - 1
        )
    }

    override fun getNextPositiveCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            row = currCoordinates.row - 1,
            column = currCoordinates.column + 1
        )
    }
}