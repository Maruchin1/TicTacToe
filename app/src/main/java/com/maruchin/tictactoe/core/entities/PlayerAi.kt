package com.maruchin.tictactoe.core.entities

import kotlinx.coroutines.delay
import kotlin.random.Random

class PlayerAi : Player("Telefon") {

    suspend fun getAiMove(board: Board): Coordinates {
        delay(MOVE_DELAY)
        val moves = getPossibleMoves(board)
        return selectRandomMove(moves)
    }

    private fun getPossibleMoves(board: Board): Array<Coordinates> {
        val result = mutableSetOf<Coordinates>()
        board.forEach { marker, coordinates ->
            if (marker == PlayerMarker.NONE) {
                result.add(coordinates)
            }
        }
        return result.toTypedArray()
    }

    private fun selectRandomMove(moves: Array<Coordinates>): Coordinates {
        val randomIdx = Random.nextInt(moves.size)
        return moves[randomIdx]
    }

    companion object {
        private const val MOVE_DELAY = 1000L
    }
}