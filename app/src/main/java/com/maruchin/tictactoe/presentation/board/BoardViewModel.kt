package com.maruchin.tictactoe.presentation.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.core.GameService
import com.maruchin.tictactoe.core.entities.PlayerMarker

class BoardViewModel(
    private val gameService: GameService
) : ViewModel() {

    val boardSize: LiveData<Int>
    val fieldsMarkers: LiveData<List<Int>>

    init {
        boardSize = getBoardSizeLive()
        fieldsMarkers = getFieldsMarkersLive()
        gameService.startNewGame(boardSize = 3)
    }

    private fun getBoardSizeLive(): LiveData<Int> {
        return Transformations.map(gameService.board) {
            it.size
        }
    }

    private fun getFieldsMarkersLive(): LiveData<List<Int>> {
        return Transformations.map(gameService.board) {
            val flatFields = it.fields.flatten()
            mapMarkersToResources(flatFields)
        }
    }

    private fun mapMarkersToResources(fields: List<PlayerMarker>): List<Int> {
        return fields.map {
            getMarkerResource(it)
        }
    }

    private fun getMarkerResource(marker: PlayerMarker): Int {
        return when (marker) {
            PlayerMarker.NONE -> 0
            PlayerMarker.CIRCLE -> R.drawable.ic_panorama_fish_eye_black_24dp
            PlayerMarker.CROSS -> R.drawable.ic_close_black_24dp
        }
    }
}