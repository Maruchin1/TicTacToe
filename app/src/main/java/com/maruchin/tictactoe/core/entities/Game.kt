package com.maruchin.tictactoe.core.entities

import com.maruchin.tictactoe.core.engine.WinningMoveChecker

class Game(
    private val winningMoveChecker: WinningMoveChecker,
    private val winningNum: Int,
    players: Pair<Player, Player>,
    boardSize: Int
) {

    val board: Board
    val gamePlayers: Pair<GamePlayer, GamePlayer>
    var moving: GamePlayer
    var winner: GamePlayer? = null

    init {
        val markers = getRandomlyOrderedMarkers()
        board = Board(boardSize)
        gamePlayers = Pair(
            first = GamePlayer(players.first, markers.first),
            second = GamePlayer(players.second, markers.second)
        )
        moving = getStartingPlayer()
    }

    fun makeMove(moveCoordinates: Coordinates) {
        board.makeMove(moveCoordinates, moving.marker)
        val isMovingWinner = winningMoveChecker.check(board, winningNum, moveCoordinates)
        if (isMovingWinner) {
            winner = moving
            moving.player.score++
        } else{
            changeMoving()
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

    private fun changeMoving() {
        moving = when (moving) {
            gamePlayers.first -> gamePlayers.second
            gamePlayers.second -> gamePlayers.first
            else -> throw Exception("Moving player is not in this game")
        }
    }
}