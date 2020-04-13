package com.maruchin.tictactoe.presentation.menu

import androidx.navigation.fragment.findNavController
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.DialogPlayDuoBinding
import com.maruchin.tictactoe.presentation.framework.BaseDialog
import org.koin.android.viewmodel.ext.android.viewModel

class DuoDialog : BaseDialog<DialogPlayDuoBinding>(R.layout.dialog_play_duo) {
    override val TAG: String
        get() = "DuoDialog"
    override val viewModel: DuoViewModel by viewModel()

    fun startSession() {
        val data = viewModel.newSessionData
        val dest = DuoDialogDirections.toGameFragment(data)
        findNavController().navigate(dest)
    }
}