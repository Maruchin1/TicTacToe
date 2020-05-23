package com.maruchin.tictactoe.presentation.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.maruchin.tictactoe.core.entities.PlayerScore
import com.maruchin.tictactoe.core.repository.PlayersScoreRepo

class ScoresViewModel(
    private val scoreRepo: PlayersScoreRepo
) : ViewModel() {

    val scores: LiveData<List<PlayerScore>> = liveData {
        val list = scoreRepo.getAll()
        val sortedList = list.sortedByDescending { it.score }
        emit(sortedList)
    }

    val scoresEmpty: LiveData<Boolean> = Transformations.map(scores) {
        it.isEmpty()
    }

}