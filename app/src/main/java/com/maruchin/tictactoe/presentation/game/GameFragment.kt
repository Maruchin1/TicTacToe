package com.maruchin.tictactoe.presentation.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.core.entities.GamePlayer
import com.maruchin.tictactoe.databinding.FragmentGameBinding
import com.maruchin.tictactoe.presentation.framework.BaseFragment
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.view_board_field.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {
    override val viewModel: GameViewModel by viewModel()
    private val args: GameFragmentArgs by navArgs()

    fun makeMove(position: Int) {
        viewModel.makeMove(position)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initSession(args.data)
        viewModel.boardSize.observe(viewLifecycleOwner, Observer {
            board_grid.layoutManager = GridLayoutManager(requireContext(), it)
        })
        viewModel.fieldsMarkers.observe(viewLifecycleOwner, Observer {
            board_grid.adapter = BoardAdapter(it)
        })
        viewModel.winner.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                showWinnerDialog(it)
            }
        })
    }

    private fun showWinnerDialog(winner: GamePlayer) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Koniec gry")
            .setMessage("${winner.player.name} wygrał grę")
            .setPositiveButton("Zagraj jeszcze raz") { _, _ ->
                viewModel.startNewGame()
            }
            .setNegativeButton("Zakończ") { _, _ -> }
            .setCancelable(false)
            .show()
    }

    inner class BoardAdapter(
        private val markers: List<Int>
    ) : RecyclerView.Adapter<FieldViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
            val inflater = LayoutInflater.from(requireContext())
            val view = inflater.inflate(R.layout.view_board_field, parent, false)
            return FieldViewHolder(view)
        }

        override fun getItemCount(): Int {
            return markers.size
        }

        override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
            val marker = markers[position]
            holder.view.apply {
                img_marker.setImageResource(marker)
                root_layout.setOnClickListener { makeMove(position) }
            }
        }
    }

    inner class FieldViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view)
}