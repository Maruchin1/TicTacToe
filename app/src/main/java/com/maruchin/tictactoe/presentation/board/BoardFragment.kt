package com.maruchin.tictactoe.presentation.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.FragmentBoardBinding
import com.maruchin.tictactoe.presentation.framework.BaseFragment
import kotlinx.android.synthetic.main.fragment_board.*
import kotlinx.android.synthetic.main.view_board_field.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class BoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {

    private val viewModel: BoardViewModel by viewModel()

    fun onClickMakeMove(position: Int) {
        viewModel.makeMove(position)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.boardSize.observe(viewLifecycleOwner, Observer {
            board_grid.layoutManager = GridLayoutManager(requireContext(), it)
        })
        viewModel.fieldsMarkers.observe(viewLifecycleOwner, Observer {
            board_grid.adapter = BoardAdapter(it)
        })
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
                root_layout.setOnClickListener { onClickMakeMove(position) }
            }
        }
    }

    inner class FieldViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view)
}