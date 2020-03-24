package com.maruchin.tictactoe.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.maruchin.tictactoe.core.engine.GameWinnerChecker
import com.maruchin.tictactoe.core.engine.WinningMoveChecker
import com.maruchin.tictactoe.core.entities.*

class GameService(
    private val winningMoveChecker: WinningMoveChecker
) {
    val board: LiveData<Board>
    val gamePlayers: LiveData<Pair<GamePlayer, GamePlayer>>
    val moving: LiveData<GamePlayer>
    val winner: LiveData<GamePlayer>

    private val gameState = MutableLiveData<Game>()

    init {
        board = Transformations.map(gameState) { it.board }
        gamePlayers = Transformations.map(gameState) { it.gamePlayers }
        moving = Transformations.map(gameState) { it.moving }
        winner = Transformations.map(gameState) { it.winner }
    }

    fun startNewGame(players: Pair<Player, Player>, boardSize: Int, winningNum: Int) {
        val newGame = Game(winningMoveChecker, winningNum, players, boardSize)
        gameState.value = newGame
    }

    fun makeMove(moveCoordinates: Coordinates) {
        val currGameState = getCurrGameState()
        currGameState.makeMove(moveCoordinates)
        gameState.value = currGameState
    }

    private fun getCurrGameState(): Game {
        return gameState.value ?: throw Exception("Game not started")
    }
}