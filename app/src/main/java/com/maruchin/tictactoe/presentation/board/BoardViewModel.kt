package com.maruchin.tictactoe.presentation.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.maruchin.tictactoe.core.GameService
import com.maruchin.tictactoe.core.entities.GamePlayer
import com.maruchin.tictactoe.core.entities.Player

class BoardViewModel(
    private val gameService: GameService,
    private val markersToResMapper: MarkersToResMapper,
    private val positionToCoordinatesMapper: PositionToCoordinatesMapper
) : ViewModel() {

    val boardSize: LiveData<Int>
    val fieldsMarkers: LiveData<List<Int>>
    val moving: LiveData<GamePlayer>
    val winner: LiveData<GamePlayer>

    init {
        boardSize = getBoardSizeLive()
        fieldsMarkers = getFieldsMarkersLive()
        moving = gameService.moving
        winner = gameService.winner
        startNewGame()
    }

    fun makeMove(position: Int) {
        val boardSize = boardSize.value!!
        val coords = positionToCoordinatesMapper.map(position, boardSize)
        gameService.makeMove(rowNum = coords.first, colNum = coords.second)
    }

    private fun getBoardSizeLive(): LiveData<Int> {
        return Transformations.map(gameService.board) {
            it.size
        }
    }

    private fun getFieldsMarkersLive(): LiveData<List<Int>> {
        return Transformations.map(gameService.board) {
            val flatFields = it.fields.flatten()
            markersToResMapper.map(flatFields)
        }
    }

    private fun startNewGame() {
        val players = Pair(
            first = Player(name = "Marcin"),
            second = Player(name = "Wojtek")
        )
        gameService.startNewGame(players, boardSize = 3)
    }
}