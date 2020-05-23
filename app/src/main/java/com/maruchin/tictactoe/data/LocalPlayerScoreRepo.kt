package com.maruchin.tictactoe.data

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.maruchin.tictactoe.core.entities.PlayerScore
import com.maruchin.tictactoe.core.repository.PlayersScoreRepo

class LocalPlayerScoreRepo(context: Context) : PlayersScoreRepo {

    companion object {
        private const val PREFS = "scores-prefs"
        private const val KEY_SCORES = "key-scores"
    }

    private val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    private val converter = Gson()

    override suspend fun addNew(score: PlayerScore) {
        val data = prefs.getStringSet(KEY_SCORES, emptySet()) ?: emptySet()
        val mutableData = data.toMutableSet()
        val singleData = converter.toJson(score)
        mutableData.add(singleData)
        prefs.edit(commit = true) {
            putStringSet(KEY_SCORES, mutableData)
        }
    }

    override suspend fun getAll(): List<PlayerScore> {
        val data = prefs.getStringSet(KEY_SCORES, emptySet()) ?: emptySet()
        return data.map {
            converter.fromJson(it, PlayerScore::class.java)
        }
    }

}