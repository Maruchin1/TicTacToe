package com.maruchin.tictactoe.core.engine.partial_checkers

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Before
import org.junit.Test

class RowCheckerTest {

    private lateinit var checker: RowChecker

    @Before
    fun before() {
        checker =
            RowChecker()
    }

    @Test
    fun shouldWin_SecondRow_FirstTwoColumns() {
        val board = Board(3).apply {
            fields[1][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CIRCLE
            fields[1][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 0)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldWin_ThirdRow_LastTwoColumns() {
        val board = Board(3).apply {
            fields[2][0] = PlayerMarker.CIRCLE
            fields[2][1] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 0)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldNoWin() {
        val board = Board(3).apply {
            fields[0][0] = PlayerMarker.CROSS
            fields[0][1] = PlayerMarker.CIRCLE
            fields[0][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 0, column = 0)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isFalse()
    }
}