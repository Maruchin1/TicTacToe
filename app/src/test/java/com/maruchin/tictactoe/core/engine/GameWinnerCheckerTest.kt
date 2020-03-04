package com.maruchin.tictactoe.core.engine

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.engine.partial_checkers.ColumnsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.DiagonalsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.RowsChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker
import com.maruchin.tictactoe.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GameWinnerCheckerTest {

    private val rowsChecker: RowsChecker = mock()
    private val columnsChecker: ColumnsChecker = mock()
    private val diagonalsChecker: DiagonalsChecker = mock()

    private lateinit var checker: GameWinnerChecker

    @Before
    fun before() {
        checker = GameWinnerChecker(
            rowsChecker,
            columnsChecker,
            diagonalsChecker
        )
    }

    @Test
    fun should_Circle_Win_In_Row() {
        val testBoard = Board(3)

        Mockito.`when`(rowsChecker.check(testBoard)).thenReturn(PlayerMarker.CIRCLE)
        Mockito.`when`(columnsChecker.check(testBoard)).thenReturn(PlayerMarker.NONE)
        Mockito.`when`(diagonalsChecker.check(testBoard)).thenReturn(PlayerMarker.NONE)

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun should_Cross_Win_In_Column() {
        val testBoard = Board(3)

        Mockito.`when`(rowsChecker.check(testBoard)).thenReturn(PlayerMarker.NONE)
        Mockito.`when`(columnsChecker.check(testBoard)).thenReturn(PlayerMarker.CROSS)
        Mockito.`when`(diagonalsChecker.check(testBoard)).thenReturn(PlayerMarker.NONE)

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CROSS)
    }

    @Test
    fun should_Cross_Win_In_Diagonal() {
        val testBoard = Board(3)

        Mockito.`when`(rowsChecker.check(testBoard)).thenReturn(PlayerMarker.NONE)
        Mockito.`when`(columnsChecker.check(testBoard)).thenReturn(PlayerMarker.NONE)
        Mockito.`when`(diagonalsChecker.check(testBoard)).thenReturn(PlayerMarker.CROSS)

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CROSS)
    }
}