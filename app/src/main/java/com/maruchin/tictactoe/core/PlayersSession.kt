package com.maruchin.tictactoe.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.maruchin.tictactoe.core.engine.WinningMoveChecker
import com.maruchin.tictactoe.core.entities.*
import com.maruchin.tictactoe.core.repository.PlayersScoreRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayersSession(
    private val winningMoveChecker: WinningMoveChecker,
    private val playersScoreRepo: PlayersScoreRepo
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

     fun endSession() = GlobalScope.launch {
         val endGameState = getCurrGameState()
         endGameState.gamePlayers.let { players ->
             savePlayerScore(players.first.player)
             savePlayerScore(players.second.player)
         }
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

    private suspend fun savePlayerScore(player: Player) {
        if (player.score > 0) {
            val playerScore = PlayerScore(player.name, player.score)
            playersScoreRepo.addNew(playerScore)
        }
    }
}