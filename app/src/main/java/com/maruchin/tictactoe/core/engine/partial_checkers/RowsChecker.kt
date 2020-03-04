package com.maruchin.tictactoe.core.engine.partial_checkers

import com.maruchin.tictactoe.core.engine.BaseWinnerChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker

class RowsChecker : BaseWinnerChecker() {

    override fun getArraysToCheck(board: Board): Array<Array<PlayerMarker>> {
        return board.fields
    }
}