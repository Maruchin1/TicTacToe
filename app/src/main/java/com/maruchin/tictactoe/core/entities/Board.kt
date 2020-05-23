package com.maruchin.tictactoe.core.entities

class Board(val size: Int) {

    val fields: Array<Array<PlayerMarker>> = Array(size) {
        Array(size) {
            PlayerMarker.NONE
        }
    }

    fun makeMove(moveCoordinates: Coordinates, marker: PlayerMarker) {
        fields[moveCoordinates.row][moveCoordinates.column] = marker
    }

    fun getForCoordinates(coordinates: Coordinates): PlayerMarker {
        return fields[coordinates.row][coordinates.column]
    }

    fun containsCoordinates(coordinates: Coordinates): Boolean {
        return coordinates.row in 0 until size &&
                coordinates.column in 0 until size
    }

    fun forEach(function: (marker: PlayerMarker, coordinates: Coordinates) -> Unit) {
        for (rowIdx in 0 until size) {
            for (colIdx in 0 until size) {
                val coordinates = Coordinates(rowIdx, colIdx)
                val marker = getForCoordinates(coordinates)
                function.invoke(marker, coordinates)
            }
        }
    }
}