package com.maruchin.tictactoe.core.entities

class Board(val size: Int) {

    val fields: Array<Array<PlayerMarker>> = Array(size) {
        Array(size) {
            PlayerMarker.NONE
        }
    }
}