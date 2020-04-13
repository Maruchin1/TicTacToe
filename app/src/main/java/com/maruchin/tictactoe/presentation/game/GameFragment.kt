package com.maruchin.tictactoe.presentation.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.core.entities.GamePlayer
import com.maruchin.tictactoe.core.entities.PlayerMarker
import com.maruchin.tictactoe.databinding.FragmentGameBinding
import com.maruchin.tictactoe.presentation.framework.BaseFragment
import com.maruchin.tictactoe.presentation.framework.ConfirmDialog
import com.maruchin.tictactoe.presentation.framework.srcPlayerMarker
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.view_board_field.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {
    override val viewModel: GameViewModel by viewModel()
    private val args: GameFragmentArgs by navArgs()

    fun makeMove(position: Int) {
        viewModel.makeMove(position)
    }

    fun endSession() {
        val dialog = ConfirmDialog(
            title = "Zakończ rozgrywkę",
            message = "Czy na pewno chcesz zakończyć rozrywkę? Nie będzie można już do niej wrócić."
        )
        dialog.onConfirm = {
            findNavController().popBackStack()
        }
        dialog.show(childFragmentManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        board_grid.adapter = BoardAdapter()
        viewModel.initSession(args.data)
        viewModel.boardSize.observe(viewLifecycleOwner, Observer {
            board_grid.layoutManager = GridLayoutManager(requireContext(), it)
        })
        viewModel.fieldsMarkers.observe(viewLifecycleOwner, Observer {
            val adapter = board_grid.adapter as BoardAdapter
            adapter.markers = it
        })
        viewModel.winner.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                showWinnerMessage(it)
                viewModel.startNewGame()
            }
        })
    }

    private fun showWinnerMessage(winner: GamePlayer) {
        val dest = GameFragmentDirections.toWinnerDialog(winner.player.name)
        findNavController().navigate(dest)
    }

    inner class BoardAdapter : RecyclerView.Adapter<FieldViewHolder>() {

        var markers: List<PlayerMarker> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
            val inflater = LayoutInflater.from(requireContext())
            val view = inflater.inflate(R.layout.view_board_field, parent, false)
            return FieldViewHolder(view)
        }

        override fun getItemCount(): Int {
            return markers.size
        }

        override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
            holder.view.apply {
                srcPlayerMarker(img_marker, markers[position])
                root_layout.setOnClickListener {
                    makeMove(position)
                }
            }
        }
    }

    inner class FieldViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}