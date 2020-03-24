package com.maruchin.tictactoe.core.entities

import com.google.common.truth.Truth
import org.junit.Test

class BoardTest {

    @Test
    fun getForCoordinates_shouldReturnTopLeft() {
        val board = Board(size = 2).apply {
            fields[0][0] = PlayerMarker.CIRCLE
        }
        val topLeft = Coordinates(row = 0, column = 0)

        val result = board.getForCoordinates(topLeft)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun getForCoordinates_shouldReturnTopRight() {
        val board = Board(size = 2).apply {
            fields[0][1] = PlayerMarker.CIRCLE
        }
        val topRight = Coordinates(row = 0, column = 1)

        val result = board.getForCoordinates(topRight)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun getForCoordinates_shouldReturnBotLeft() {
        val board = Board(size = 2).apply {
            fields[1][0] = PlayerMarker.CIRCLE
        }
        val botLeft = Coordinates(row = 1, column = 0)

        val result = board.getForCoordinates(botLeft)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun getForCoordinates_shouldReturnBotRight() {
        val board = Board(size = 2).apply {
            fields[1][1] = PlayerMarker.CIRCLE
        }
        val botRight = Coordinates(row = 1, column = 1)

        val result = board.getForCoordinates(botRight)

        Truth.assertThat(result).isEqualTo(PlayerMarker.CIRCLE)
    }

    @Test
    fun containsCoordinates_shouldContainTopLeft() {
        val board = Board(size = 2)
        val topLeft = Coordinates(row = 0, column = 0)

        val result = board.containsCoordinates(topLeft)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun containsCoordinates_shouldContainTopRight() {
        val board = Board(size = 2)
        val topRight = Coordinates(row = 0, column = 1)

        val result = board.containsCoordinates(topRight)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun containsCoordinates_shouldContainBotLeft() {
        val board = Board(size = 2)
        val botLeft = Coordinates(row = 1, column = 0)

        val result = board.containsCoordinates(botLeft)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun containsCoordinates_shouldContainBotRight() {
        val board = Board(size = 2)
        val botRight = Coordinates(row = 1, column = 1)

        val result = board.containsCoordinates(botRight)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun containsCoordinates_shouldNotContainOutsideLeftBorder() {
        val board = Board(size = 2)
        val outsideLeftBorder = Coordinates(row = 0, column = -1)

        val result = board.containsCoordinates(outsideLeftBorder)

        Truth.assertThat(result).isFalse()
    }

    @Test
    fun containsCoordinates_shouldNotContainOutsideTopBorder() {
        val board = Board(size = 2)
        val outsideTopBorder = Coordinates(row = -1, column = 0)

        val result = board.containsCoordinates(outsideTopBorder)

        Truth.assertThat(result).isFalse()
    }

    @Test
    fun containsCoordinates_shouldNotContainOutsideRightTopBorder() {
        val board = Board(size = 2)
        val outsideRightBorder = Coordinates(row = 1, column = 2)

        val result = board.containsCoordinates(outsideRightBorder)

        Truth.assertThat(result).isFalse()
    }

    @Test
    fun containsCoordinates_shouldNotContainOutsideBotBorder() {
        val board = Board(size = 2)
        val outsideBotBorder = Coordinates(row = 2, column = 1)

        val result = board.containsCoordinates(outsideBotBorder)

        Truth.assertThat(result).isFalse()
    }
}