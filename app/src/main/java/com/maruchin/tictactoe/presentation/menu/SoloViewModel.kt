package com.maruchin.tictactoe.presentation.menu

import androidx.lifecycle.*
import com.maruchin.tictactoe.presentation.framework.Validators
import com.maruchin.tictactoe.presentation.game.NewSessionData

class SoloViewModel : ViewModel() {


    val playerName = MutableLiveData<String>()

    val errorPlayerName: LiveData<String> = Transformations.map(playerName) {
        Validators.playerName(it)
    }

    val isFormValid: LiveData<Boolean> = initFormValidator()

    val newSessionData: NewSessionData
        get() = NewSessionData(
            firstPlayerName = playerName.value!!.trim(),
            secondPlayerName = null
        )

    private fun initFormValidator(): LiveData<Boolean> {
        return Transformations.map(errorPlayerName) {
            it == null
        }
    }
}