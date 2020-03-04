package com.maruchin.tictactoe.presentation.board

import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.core.entities.PlayerMarker

class MarkersToResMapper {

    fun map(markers: List<PlayerMarker>): List<Int> {
        return markers.map {
            getResForMarker(it)
        }
    }

    private fun getResForMarker(marker: PlayerMarker): Int {
        return when (marker) {
            PlayerMarker.NONE -> 0
            PlayerMarker.CIRCLE -> R.drawable.ic_panorama_fish_eye_black_24dp
            PlayerMarker.CROSS -> R.drawable.ic_close_black_24dp
        }
    }
}