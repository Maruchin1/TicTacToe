package com.maruchin.tictactoe.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.tictactoe.core.entities.PlayerAi
import com.maruchin.tictactoe.core.PlayersSession
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.GamePlayer
import com.maruchin.tictactoe.core.entities.Player
import com.maruchin.tictactoe.core.entities.PlayerMarker
import kotlinx.coroutines.launch

class GameViewModel(
    private val playersSession: PlayersSession,
    private val positionToCoordinatesMapper: PositionToCoordinatesMapper
) : ViewModel() {

    companion object {
        private const val BOARD_SIZE = 10
        private const val WINNING_NUM = 5
    }

    val players: LiveData<Pair<GamePlayer, GamePlayer>> = playersSession.gamePlayers

    val boardSize: LiveData<Int> = Transformations.map(playersSession.board) {
        it.size
    }

    val fieldsMarkers: LiveData<List<PlayerMarker>> = Transformations.map(playersSession.board) {
        it.fields.flatten()
    }

    val moving: LiveData<GamePlayer> = playersSession.moving

    val winner: LiveData<GamePlayer> = playersSession.winner

    fun initSession(data: NewSessionData) {
        val players: Pair<Player, Player>
        if (data.secondPlayerName != null) {
            players = makeDuoPlayers(data.firstPlayerName, data.secondPlayerName)
        } else {
            players = makeSoloPlayers(data.firstPlayerName)
            observeForAiMove()
        }
        playersSession.apply {
            initSession(players, BOARD_SIZE, WINNING_NUM)
            startNewGame()
        }
    }

    fun startNewGame() = playersSession.startNewGame()

    fun makeMove(position: Int) {
        val boardSize = boardSize.value!!
        val moveCoordinates = positionToCoordinatesMapper.map(position, boardSize)
        playersSession.makeMove(moveCoordinates)
    }

    private fun makeSoloPlayers(playerName: String) = Pair(
        first = Player(playerName),
        second = PlayerAi()
    )

    private fun makeDuoPlayers(firstPlayerName: String, secondPlayerName: String) = Pair(
        first = Player(firstPlayerName),
        second = Player(secondPlayerName)
    )

    private fun observeForAiMove() {
        var boardState: Board? = null
        playersSession.board.observeForever { board ->
            boardState = board
        }
        playersSession.moving.observeForever { moving ->
            if (moving.player is PlayerAi) {
                boardState?.let { state ->
                    makeAiMove(moving.player, state)
                }
            }
        }
    }

    private fun makeAiMove(player: PlayerAi, boardState: Board) = viewModelScope.launch {
        val move = player.getAiMove(boardState)
        playersSession.makeMove(move)
    }
}