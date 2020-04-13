package com.maruchin.tictactoe.presentation.menu

import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.DialogPlaySoloBinding
import com.maruchin.tictactoe.presentation.framework.BaseDialog

class SoloDialog : BaseDialog<DialogPlaySoloBinding>(R.layout.dialog_play_solo){
    override val TAG: String
        get() = "SoloDialog"
}