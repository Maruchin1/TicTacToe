package com.maruchin.tictactoe.presentation.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.maruchin.tictactoe.BR
import com.maruchin.tictactoe.R

abstract class BaseDialog<T : ViewDataBinding>(private val layoutResId: Int): DialogFragment() {
    protected open val viewModel: ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.App_Theme_Dialog)
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: T = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.apply {
            setVariable(BR.controller, this@BaseDialog)
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}