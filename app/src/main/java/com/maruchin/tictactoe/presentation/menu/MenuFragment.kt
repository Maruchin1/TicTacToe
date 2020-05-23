package com.maruchin.tictactoe.presentation.menu

import androidx.navigation.fragment.findNavController
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.FragmentMenuBinding
import com.maruchin.tictactoe.presentation.framework.BaseFragment

class MenuFragment : BaseFragment<FragmentMenuBinding>(R.layout.fragment_menu) {

    fun playSolo() {
        val dest = MenuFragmentDirections.toSoloDialog()
        findNavController().navigate(dest)
    }

    fun playDuo() {
        val dest = MenuFragmentDirections.toDuoDialog()
        findNavController().navigate(dest)
    }

    fun showScores() {
        val dest = MenuFragmentDirections.toScoresFragment()
        findNavController().navigate(dest)
    }

    fun exitGame() {
        requireActivity().finish()
    }
}