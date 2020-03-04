package com.maruchin.tictactoe.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.maruchin.tictactoe.BR

abstract class BaseFragment<T : ViewDataBinding>(private val layoutResId: Int) : Fragment() {

    protected val bindingViewModel: ViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: T = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.apply {
            setVariable(BR.controller, this@BaseFragment)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}