package com.maruchin.tictactoe.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.maruchin.tictactoe.core.engine.WinningMoveChecker
import com.maruchin.tictactoe.core.entities.*

class PlayersSession(
    private val winningMoveChecker: WinningMoveChecker
) {
    val board: LiveData<Board>
    val gamePlayers: LiveData<Pair<GamePlayer, GamePlayer>>
    val moving: LiveData<GamePlayer>
    val winner: LiveData<GamePlayer>

    private val gameState = MutableLiveData<Game>()
    private var players: Pair<Player, Player>? = null
    private var boardSize: Int? = null
    private var winningNum: Int? = null

    init {
        board = Transformations.map(gameState) { it.board }
        gamePlayers = Transformations.map(gameState) { it.gamePlayers }
        moving = Transformations.map(gameState) { it.moving }
        winner = Transformations.map(gameState) { it.winner }
    }

    fun initSession(players: Pair<Player, Player>, boardSize: Int, winningNum: Int) {
        this.players = players
        this.boardSize = boardSize
        this.winningNum = winningNum
    }

    fun startNewGame() {
        if (arrayOf(players, boardSize, winningNum).any { it == null }) {
            throw Exception("Session has to be initialized before starting game")
        }
        val newGame = Game(winningMoveChecker, winningNum!!, players!!, boardSize!!)
        gameState.value = newGame
    }

    fun makeMove(move: Coordinates) {
        val currGameState = getCurrGameState()
        currGameState.makeMove(move)
        gameState.value = currGameState
    }

    private fun getCurrGameState(): Game {
        return gameState.value ?: throw Exception("Game not started")
    }
}