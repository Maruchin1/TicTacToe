package com.maruchin.tictactoe.presentation.game

import androidx.navigation.fragment.navArgs
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.DialogWinnerBinding
import com.maruchin.tictactoe.presentation.framework.BaseDialog

class WinnerDialog : BaseDialog<DialogWinnerBinding>(R.layout.dialog_winner) {
    override val TAG: String
        get() = "WinnerDialog"
    val args: WinnerDialogArgs by navArgs()
}