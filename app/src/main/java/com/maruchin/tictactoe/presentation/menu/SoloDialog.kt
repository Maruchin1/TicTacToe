package com.maruchin.tictactoe.presentation.menu

import androidx.navigation.fragment.findNavController
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.DialogPlaySoloBinding
import com.maruchin.tictactoe.presentation.framework.BaseDialog
import org.koin.android.viewmodel.ext.android.viewModel

class SoloDialog : BaseDialog<DialogPlaySoloBinding>(R.layout.dialog_play_solo){
    override val TAG: String
        get() = "SoloDialog"
    override val viewModel: SoloViewModel by viewModel()

    fun startSession() {
        val data = viewModel.newSessionData
        val dest = SoloDialogDirections.toGameFragment(data)
        findNavController().navigate(dest)
    }
}