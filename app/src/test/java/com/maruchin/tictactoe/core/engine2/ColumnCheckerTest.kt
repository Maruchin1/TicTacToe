package com.maruchin.tictactoe.core.engine2

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.engine2.partial_checkers.ColumnChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Before
import org.junit.Test

class ColumnCheckerTest {

    private lateinit var checker: ColumnChecker

    @Before
    fun before() {
        checker = ColumnChecker()
    }

    @Test
    fun shouldWin_FirstColumn_FirstTwoRows() {
        val board = Board(3).apply {
            fields[0][0] = PlayerMarker.CIRCLE
            fields[1][0] = PlayerMarker.CIRCLE
            fields[2][0] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 0)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldWin_SecondColumn_LastTwoRows() {
        val board = Board(3).apply {
            fields[0][1] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CROSS
            fields[2][1] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 2, column = 1)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldNotWin() {
        val board = Board(3).apply {
            fields[0][2] = PlayerMarker.CIRCLE
            fields[1][2] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CIRCLE
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 0, column = 2)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isFalse()
    }
}