package com.maruchin.tictactoe.core.engine.partial_checkers

import com.maruchin.tictactoe.core.engine.OneAxisChecker
import com.maruchin.tictactoe.core.entities.Coordinates

class RowChecker : OneAxisChecker() {

    override fun getNextNegativeCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            column = currCoordinates.column - 1
        )
    }

    override fun getNextPositiveCoordinates(currCoordinates: Coordinates): Coordinates {
        return currCoordinates.copy(
            column = currCoordinates.column + 1
        )
    }
}