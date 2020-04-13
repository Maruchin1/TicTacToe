package com.maruchin.tictactoe.presentation.framework

import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.DialogConfirmBinding

class ConfirmDialog(
    val title: String,
    val message: String
) : BaseDialog<DialogConfirmBinding>(R.layout.dialog_confirm) {
    override val TAG: String
        get() = "ConfirmDialog"

    var onConfirm: (() -> Unit)? = null
}