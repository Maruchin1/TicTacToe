package com.maruchin.tictactoe.presentation.menu

import androidx.lifecycle.*
import com.maruchin.tictactoe.presentation.framework.Validators
import com.maruchin.tictactoe.presentation.game.NewSessionData

class DuoViewModel : ViewModel() {

    val firstPlayerName = MutableLiveData<String>()
    val secondPlayerName = MutableLiveData<String>()

    val errorFirstPlayerName: LiveData<String> = Transformations.map(firstPlayerName) {
        Validators.playerName(it)
    }

    val errorSecondPlayerName: LiveData<String> = Transformations.map(secondPlayerName) {
        Validators.playerName(it)
    }

    val isFormValid: LiveData<Boolean> = initFormValidator()

    val newSessionData: NewSessionData
        get() = NewSessionData(
            firstPlayerName = firstPlayerName.value!!.trim(),
            secondPlayerName = secondPlayerName.value!!.trim()
        )

    private fun initFormValidator(): LiveData<Boolean> {
        val valid = MediatorLiveData<Boolean>()
        var firstPlayerNameValid = false
        var secondPlayerNameValid = false
        valid.addSource(errorFirstPlayerName) { error ->
            firstPlayerNameValid = error == null
            valid.value = firstPlayerNameValid && secondPlayerNameValid
        }
        valid.addSource(errorSecondPlayerName) { error ->
            secondPlayerNameValid = error == null
            valid.value = firstPlayerNameValid && secondPlayerNameValid
        }
        return valid
    }
}