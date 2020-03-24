package com.maruchin.tictactoe.core.engine2

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.engine2.partial_checkers.ColumnChecker
import com.maruchin.tictactoe.core.engine2.partial_checkers.FirstDiagonalChecker
import com.maruchin.tictactoe.core.engine2.partial_checkers.RowChecker
import com.maruchin.tictactoe.core.engine2.partial_checkers.SecondDiagonalChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.Coordinates
import com.maruchin.tictactoe.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class WinningMoveCheckerTest {

    private val rowChecker: RowChecker = mock()
    private val columnChecker: ColumnChecker = mock()
    private val firstDiagonalChecker: FirstDiagonalChecker = mock()
    private val secondDiagonalChecker: SecondDiagonalChecker = mock()

    private lateinit var checker: WinningMoveChecker

    @Before
    fun before() {
        checker = WinningMoveChecker(
            rowChecker,
            columnChecker,
            firstDiagonalChecker,
            secondDiagonalChecker
        )
    }

    @Test
    fun shouldWin() {
        val board = Board(size = 3)
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        `when`(rowChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(false)
        `when`(columnChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(true)
        `when`(firstDiagonalChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(false)
        `when`(secondDiagonalChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(false)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun shouldNotWin() {
        val board = Board(size = 3)
        val winningNum = 2
        val moveCoordinates = Coordinates(row = 1, column = 1)

        `when`(rowChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(false)
        `when`(columnChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(false)
        `when`(firstDiagonalChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(false)
        `when`(secondDiagonalChecker.check(board, winningNum, moveCoordinates))
            .thenReturn(false)

        val result = checker.check(board, winningNum, moveCoordinates)

        Truth.assertThat(result).isTrue()
    }
}