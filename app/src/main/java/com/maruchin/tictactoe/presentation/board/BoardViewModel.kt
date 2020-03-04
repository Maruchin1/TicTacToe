package com.maruchin.tictactoe.presentation.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.maruchin.tictactoe.core.GameService
import com.maruchin.tictactoe.core.entities.PlayerMarker
import kotlin.Exception

class BoardViewModel(
    private val gameService: GameService,
    private val markersToResMapper: MarkersToResMapper,
    private val positionToCoordinatesMapper: PositionToCoordinatesMapper
) : ViewModel() {

    val boardSize: LiveData<Int>
    val fieldsMarkers: LiveData<List<Int>>

    private var currPlayer: PlayerMarker = PlayerMarker.CIRCLE

    init {
        boardSize = getBoardSizeLive()
        fieldsMarkers = getFieldsMarkersLive()
        gameService.startNewGame(boardSize = 3)
    }

    fun makeMove(position: Int) {
        val boardSize = boardSize.value ?: throw Exception("Game not started")
        val coords = positionToCoordinatesMapper.map(position, boardSize)
        gameService.makeMove(
            rowNum = coords.first,
            colNum = coords.second,
            marker = currPlayer
        )
        changeCurrPlayer()
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

    private fun changeCurrPlayer() {
        currPlayer = when (currPlayer) {
            PlayerMarker.CIRCLE -> PlayerMarker.CROSS
            PlayerMarker.CROSS -> PlayerMarker.CIRCLE
            else -> throw Exception("Player cant be NONE")
        }
    }
}