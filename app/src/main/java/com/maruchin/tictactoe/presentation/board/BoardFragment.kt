package com.maruchin.tictactoe.presentation.board

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.databinding.FragmentBoardBinding
import com.maruchin.tictactoe.presentation.framework.BaseFragment

class BoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {

    inner class BoardAdapter : BaseAdapter() {

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItem(p0: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(p0: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}