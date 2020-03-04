package com.maruchin.tictactoe.presentation.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.core.entities.PlayerMarker
import com.maruchin.tictactoe.databinding.FragmentBoardBinding
import com.maruchin.tictactoe.presentation.framework.BaseFragment
import kotlinx.android.synthetic.main.fragment_board.*
import org.koin.android.viewmodel.ext.android.viewModel

class BoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {

    private val viewModel: BoardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.boardSize.observe(viewLifecycleOwner, Observer {
            board_grid.layoutManager = GridLayoutManager(requireContext(), it)
        })
        viewModel.fields.observe(viewLifecycleOwner, Observer {
            board_grid.adapter = BoardAdapter(it)
        })
    }

    inner class BoardAdapter(
        private val fields: List<PlayerMarker>
    ) : RecyclerView.Adapter<FieldViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
            val inflater = LayoutInflater.from(requireContext())
            val view = inflater.inflate(R.layout.view_board_field, parent, false)
            return FieldViewHolder(view)
        }

        override fun getItemCount(): Int {
            return fields.size
        }

        override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {

        }
    }

    inner class FieldViewHolder(
        private val itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

    }
}