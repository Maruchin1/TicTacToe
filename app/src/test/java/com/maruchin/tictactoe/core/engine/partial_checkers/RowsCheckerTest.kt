package com.maruchin.tictactoe.core.engine.partial_checkers

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Before
import org.junit.Test

class RowsCheckerTest {

    private lateinit var checker: RowsChecker

    @Before
    fun before() {
        checker =
            RowsChecker()
    }

    @Test
    fun should_Circle_Win_In_Second_Row() {
        val testBoard = Board(3)
        testBoard.apply {
            fields[1][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CIRCLE
            fields[1][2] = PlayerMarker.CIRCLE
        }

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun should_Cross_Win_In_Third_Row() {
        val testBoard = Board(3)
        testBoard.apply {
            fields[2][0] = PlayerMarker.CROSS
            fields[2][1] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CROSS
        }

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CROSS)
    }

    @Test
    fun should_None_Win() {
        val testBoard = Board(3)
        testBoard.apply {
            fields[0][0] = PlayerMarker.CROSS
            fields[1][2] = PlayerMarker.CIRCLE
            fields[2][0] = PlayerMarker.NONE
        }

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.NONE)
    }
}