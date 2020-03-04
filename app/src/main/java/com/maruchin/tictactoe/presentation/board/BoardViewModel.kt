package com.maruchin.tictactoe.presentation.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.maruchin.tictactoe.core.GameService
import com.maruchin.tictactoe.core.entities.PlayerMarker

class BoardViewModel(
    private val gameService: GameService
) : ViewModel() {

    val boardSize: LiveData<Int>
    val fields: LiveData<List<PlayerMarker>>

    init {
        boardSize = getBoardSizeLive()
        fields = getFieldsLive()
        gameService.startNewGame(boardSize = 3)
    }

    private fun getBoardSizeLive(): LiveData<Int> {
        return Transformations.map(gameService.board) {
            it.size
        }
    }

    private fun getFieldsLive(): LiveData<List<PlayerMarker>> {
        return Transformations.map(gameService.board) {
            it.fields.flatten()
        }
    }
}