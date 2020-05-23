package com.maruchin.tictactoe.presentation.framework

object Validators {

    fun playerName(value: String?): String? {
        return if (value.isNullOrEmpty()) {
            "Podanie imienia jest wymagane"
        } else {
            null
        }
    }
}