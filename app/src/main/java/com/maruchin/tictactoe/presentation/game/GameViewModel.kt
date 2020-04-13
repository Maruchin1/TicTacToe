package com.maruchin.tictactoe.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.maruchin.tictactoe.core.PlayersSession
import com.maruchin.tictactoe.core.entities.GamePlayer
import com.maruchin.tictactoe.core.entities.Player

class GameViewModel(
    private val playersSession: PlayersSession,
    private val markersToResMapper: MarkersToResMapper,
    private val positionToCoordinatesMapper: PositionToCoordinatesMapper
) : ViewModel() {

    companion object {
        private const val BOARD_SIZE = 10
        private const val WINNING_NUM = 5
    }

    val playersScores: LiveData<Pair<PlayerScore, PlayerScore>>
    val boardSize: LiveData<Int>
    val fieldsMarkers: LiveData<List<Int>>
    val moving: LiveData<GamePlayer>
    val winner: LiveData<GamePlayer>

    init {
        playersScores = getPlayersScoresLive()
        boardSize = getBoardSizeLive()
        fieldsMarkers = getFieldsMarkersLive()
        moving = playersSession.moving
        winner = playersSession.winner
    }

    fun initSession(data: NewSessionData) {
        val players = Pair(
            first = Player(data.firstPlayerName),
            second = Player(data.secondPlayerName)
        )
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

    private fun getPlayersScoresLive(): LiveData<Pair<PlayerScore, PlayerScore>> {
        return Transformations.map(playersSession.gamePlayers) {
            val firstPlayerScore = makePlayerScore(it.first)
            val secondPlayerScore = makePlayerScore(it.second)
            Pair(firstPlayerScore, secondPlayerScore)
        }
    }

    private fun getBoardSizeLive(): LiveData<Int> {
        return Transformations.map(playersSession.board) {
            it.size
        }
    }

    private fun getFieldsMarkersLive(): LiveData<List<Int>> {
        return Transformations.map(playersSession.board) {
            val flatFields = it.fields.flatten()
            markersToResMapper.map(flatFields)
        }
    }

    private fun makePlayerScore(gamePlayer: GamePlayer): PlayerScore {
        return PlayerScore(
            playerName = gamePlayer.player.name,
            score = gamePlayer.player.score.toString()
        )
    }
}