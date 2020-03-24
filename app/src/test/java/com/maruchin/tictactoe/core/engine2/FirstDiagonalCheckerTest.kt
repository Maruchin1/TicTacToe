package com.maruchin.tictactoe.core.engine2

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.engine2.partial_checkers.FirstDiagonalChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Before
import org.junit.Test

class FirstDiagonalCheckerTest {

    private lateinit var checker: FirstDiagonalChecker

    @Before
    fun before() {
        checker = FirstDiagonalChecker()
    }

    @Test
    fun shouldWin_FirstRowFirstColumn_SecondRowSecondColumn() {
        val board = Board(size = 3).apply {
            fields[0][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CIRCLE
            fields[2][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldWin_SecondRowSecondColumn_ThirdRowThirdColumn() {
        val board = Board(size = 3).apply {
            fields[0][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldNotWin() {
        val board = Board(size = 3).apply {
            fields[0][0] = PlayerMarker.CROSS
            fields[1][1] = PlayerMarker.CIRCLE
            fields[2][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isFalse()
    }
}