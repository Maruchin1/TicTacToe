package com.maruchin.tictactoe.core.engine2

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Assert.*
import org.junit.Test

class ColumnCheckerTest {

    @Test
    fun should_Win_In_First_Column_And_First_Two_Rows() {
        val board = Board(3).apply {
            fields[0][0] = PlayerMarker.CIRCLE
            fields[1][0] = PlayerMarker.CIRCLE
            fields[2][0] = PlayerMarker.CROSS
        }
        val checker = ColumnChecker(
            board = board,
            winningNum = 2,
            moveCoordinates = Coordinates(row = 1, column = 0)
        )

        val result = checker.check()

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun should_Win_In_Second_Column_And_Last_Two_Rows() {
        val board = Board(3).apply {
            fields[0][1] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CROSS
            fields[2][1] = PlayerMarker.CROSS
        }
        val checker = ColumnChecker(
            board = board,
            winningNum = 2,
            moveCoordinates = Coordinates(row = 2, column = 1)
        )

        val result = checker.check()

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun should_Not_Win() {
        val board = Board(3).apply {
            fields[0][2] = PlayerMarker.CIRCLE
            fields[1][2] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CIRCLE
        }
        val checker = ColumnChecker(
            board = board,
            winningNum = 2,
            moveCoordinates = Coordinates(row = 0, column = 2)
        )

        val result = checker.check()

        Truth.assertThat(result).isFalse()
    }
}