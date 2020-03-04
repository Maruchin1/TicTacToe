package com.maruchin.tictactoe.presentation.board

class PositionToCoordinatesMapper {

    fun map(position: Int, boardSize: Int): Pair<Int, Int> {
        val rowNum = calcRowNum(position, boardSize)
        val colNum = calcColNum(position, boardSize)
        return Pair(rowNum, colNum)
    }

    private fun calcRowNum(position: Int, boardSize: Int): Int {
        return position / boardSize
    }

    private fun calcColNum(position: Int, boardSize: Int): Int {
        return position % boardSize
    }
}