package com.maruchin.tictactoe.core.engine2

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Test

class RowCheckerTest {

    @Test
    fun should_Win_In_Second_Row_And_First_Two_Columns() {
        val board = Board(3).apply {
            fields[1][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CIRCLE
            fields[1][2] = PlayerMarker.CROSS
        }
        val checker = RowChecker(
            board = board,
            winningNum = 2,
            moveCoordinates = Coordinates(row = 1, column = 0)
        )

        val result = checker.check()

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun should_Win_In_Third_Row_And_Last_Two_Columns() {
        val board = Board(3).apply {
            fields[2][0] = PlayerMarker.CIRCLE
            fields[2][1] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CROSS
        }
        val checker = RowChecker(
            board = board,
            winningNum = 2,
            moveCoordinates = Coordinates(row = 1, column = 0)
        )

        val result = checker.check()

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun should_No_Win() {
        val board = Board(3).apply {
            fields[0][0] = PlayerMarker.CROSS
            fields[0][1] = PlayerMarker.CIRCLE
            fields[0][2] = PlayerMarker.CROSS
        }
        val checker = RowChecker(
            board = board,
            winningNum = 2,
            moveCoordinates = Coordinates(row = 0, column = 0)
        )

        val result = checker.check()

        Truth.assertThat(result).isFalse()
    }
}