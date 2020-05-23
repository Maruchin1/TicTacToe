package com.maruchin.tictactoe.core.repository

import com.maruchin.tictactoe.core.entities.PlayerScore

interface PlayersScoreRepo {

    suspend fun addNew(score: PlayerScore)

    suspend fun getAll(): List<PlayerScore>
}