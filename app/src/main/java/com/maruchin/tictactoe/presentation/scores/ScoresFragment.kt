package com.maruchin.tictactoe.presentation.scores

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.core.entities.PlayerScore
import com.maruchin.tictactoe.databinding.FragmentScoresBinding
import com.maruchin.tictactoe.presentation.framework.AppRecyclerViewAdapter
import com.maruchin.tictactoe.presentation.framework.AppViewHolder
import com.maruchin.tictactoe.presentation.framework.BaseFragment
import kotlinx.android.synthetic.main.fragment_scores.*
import kotlinx.android.synthetic.main.item_score.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ScoresFragment : BaseFragment<FragmentScoresBinding>(R.layout.fragment_scores) {
    override val viewModel: ScoresViewModel by viewModel()

    fun backToMenu() {
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scores_recycler.apply {
            adapter = ScoresAdapter()
            val divider = DividerItemDecoration(
                requireContext(),
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(divider)
        }
        viewModel.scores.observe(viewLifecycleOwner, Observer {
            val adapter = scores_recycler.adapter as ScoresAdapter
            adapter.items = it
        })
    }

    inner class ScoresAdapter : AppRecyclerViewAdapter<PlayerScore>(requireContext(), R.layout.item_score) {
        override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
            val item = items[position]
            val rankingPosition = position + 1
            val rankingPositionText = "$rankingPosition."
            holder.itemView.apply {
                number.text = rankingPositionText
                name.text = item.playerName
                score.text = item.score.toString()
            }
        }
    }
}