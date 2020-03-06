package com.maruchin.tictactoe.core.entities

import com.maruchin.tictactoe.core.engine.GameWinnerChecker

class Game(
    private val gameWinnerChecker: GameWinnerChecker,
    private val winningNum: Int,
    players: Pair<Player, Player>,
    boardSize: Int
) {

    val board: Board
    val gamePlayers: Pair<GamePlayer, GamePlayer>
    var moving: GamePlayer

    init {
        val markers = getRandomlyOrderedMarkers()
        board = Board(boardSize)
        gamePlayers = Pair(
            first = GamePlayer(players.first, markers.first),
            second = GamePlayer(players.second, markers.second)
        )
        moving = getStartingPlayer()
    }

    fun makeMove(rowNum: Int, colNum: Int) {
        board.fields[rowNum][colNum] = moving.marker
        moving = getNextMoving()
    }

    fun checkWinner(): GamePlayer? {
        val winnerMarker = gameWinnerChecker.check(board, winningNum)
        return gamePlayers.toList().find {
            it.marker == winnerMarker
        }
    }

    private fun getRandomlyOrderedMarkers(): Pair<PlayerMarker, PlayerMarker> {
        val markers = listOf(PlayerMarker.CROSS, PlayerMarker.CIRCLE)
        val randomOrderList = markers.shuffled()
        return Pair(randomOrderList[0], randomOrderList[1])
    }

    private fun getStartingPlayer(): GamePlayer {
        return gamePlayers.toList().first {
            it.marker == PlayerMarker.CROSS
        }
    }

    private fun getNextMoving(): GamePlayer {
        return when (moving) {
            gamePlayers.first -> gamePlayers.second
            gamePlayers.second -> gamePlayers.first
            else -> throw Exception("Moving player is not in this game")
        }
    }
}