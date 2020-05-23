package com.maruchin.tictactoe.presentation.game

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewSessionData(
    val firstPlayerName: String,
    val secondPlayerName: String?
) : Parcelable