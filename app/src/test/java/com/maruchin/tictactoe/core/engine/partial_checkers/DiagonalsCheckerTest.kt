package com.maruchin.tictactoe.core.engine.partial_checkers

import com.google.common.truth.Truth
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker
import org.junit.Before
import org.junit.Test

class DiagonalsCheckerTest {

    private lateinit var checker: DiagonalsChecker

    @Before
    fun before() {
        checker = DiagonalsChecker()
    }

    @Test
    fun should_Circle_Win_In_First_Diagonal() {
        val boardSize = 3
        val winningNum = 2
        val testBoard = Board(boardSize)
        testBoard.apply {
            fields[0][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CIRCLE
            fields[2][2] = PlayerMarker.CROSS
        }

        val result = checker.check(testBoard, winningNum)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun should_Cross_Win_In_Second_Diagonal() {
        val boardSize = 3
        val winningNum = 2
        val testBoard = Board(boardSize)
        testBoard.apply {
            fields[0][2] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CROSS
            fields[2][0] = PlayerMarker.CROSS
        }

        val result = checker.check(testBoard, winningNum)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CROSS)
    }

    @Test
    fun should_None_Win() {
        val boardSize = 3
        val winningNum = 2
        val testBoard = Board(boardSize)
        testBoard.apply {
            fields[0][0] = PlayerMarker.CIRCLE
            fields[1][1] = PlayerMarker.CROSS
            fields[2][2] = PlayerMarker.CIRCLE
        }

        val result = checker.check(testBoard, winningNum)

        Truth.assertThat(result).isEqualTo(PlayerMarker.NONE)
    }
}