package com.maruchin.tictactoe.core.entities

class Board(val size: Int) {

    val fields: Array<Array<PlayerMarker>>

    init {
        fields = initBoard()
    }

    private fun initBoard(): Array<Array<PlayerMarker>> {
        return Array(size) {
            Array(size) {
                PlayerMarker.NONE
            }
        }
    }
}