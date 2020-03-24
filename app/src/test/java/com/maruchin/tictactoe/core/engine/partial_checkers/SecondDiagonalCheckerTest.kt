package com.maruchin.tictactoe.core.engine.partial_checkers

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Before
import org.junit.Test

class SecondDiagonalCheckerTest {

    private lateinit var checker: SecondDiagonalChecker

    @Before
    fun before() {
        checker =
            SecondDiagonalChecker()
    }

    @Test
    fun shouldWin_ThirdRowFirstColumn_SecondRowSecondColumn() {
        val board = Board(size = 3).apply {
            fields[2][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CIRCLE
            fields[0][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldWin_SecondRowSecondColumn_FirstRowThirdColumn() {
        val board = Board(size = 3).apply {
            fields[2][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CROSS
            fields[0][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldNotWin() {
        val board = Board(size = 3).apply {
            fields[2][0] = PlayerMarker.CROSS
            fields[1][1] = PlayerMarker.CIRCLE
            fields[0][2] = PlayerMarker.CROSS
        }
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isFalse()
    }
}