package com.maruchin.tictactoe.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maruchin.tictactoe.core.engine.GameWinnerChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.ColumnsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.DiagonalsChecker
import com.maruchin.tictactoe.core.engine.partial_checkers.RowsChecker
import com.maruchin.tictactoe.core.entities.Board
import com.maruchin.tictactoe.core.entities.PlayerMarker
import java.lang.Exception

class GameService(
    private val gameWinnerChecker: GameWinnerChecker
) {
    val board: LiveData<Board>
        get() = _board
    val winner: LiveData<PlayerMarker>
        get() = _winner

    private val _board = MutableLiveData<Board>()
    private val _winner = MutableLiveData<PlayerMarker>()


    fun startNewGame(boardSize: Int) {
        _board.value = Board(boardSize)
        _winner.value = PlayerMarker.NONE
    }

    fun makeMove(rowNum: Int, colNum: Int, marker: PlayerMarker) {
        val currBoard = getCurrBoard()
        currBoard.fields[rowNum][colNum] = marker
        _board.value = currBoard
        checkWinner()
    }

    private fun getCurrBoard(): Board {
        return _board.value ?: throw Exception("Game was not started")
    }

    private fun checkWinner() {
        val currBoard = getCurrBoard()
        val currWinner = gameWinnerChecker.check(currBoard)
        _winner.value = currWinner
    }
}