package com.maruchin.tictactoe.core.engine.partial_checkers

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Before
import org.junit.Test

class ColumnsCheckerTest {

    private lateinit var checker: ColumnsChecker

    @Before
    fun before() {
        checker =
            ColumnsChecker()
    }

    @Test
    fun should_Circle_Win_In_First_Column() {
        val testBoard = Board(3)
        testBoard.apply {
            fields[0][0] = PlayerMarker.CIRCLE
            fields[1][0] = PlayerMarker.CIRCLE
            fields[2][0] = PlayerMarker.CIRCLE
        }

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun should_Cross_Win_In_Third_Column() {
        val testBoard = Board(3)
        testBoard.apply {
            fields[0][2] = PlayerMarker.CROSS
            fields[1][2] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CROSS
        }

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CROSS)
    }

    @Test
    fun should_None_Win() {
        val testBoard = Board(3)
        testBoard.apply {
            fields[0][1] = PlayerMarker.CIRCLE
            fields[2][1] = PlayerMarker.CROSS
        }

        val result = checker.check(testBoard)

        Truth.assertThat(result).isEqualTo(PlayerMarker.NONE)
    }
}