package com.maruchin.tictactoe.presentation.framework

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("inputError")
fun inputError(input: TextInputLayout, error: String?) {
    input.error = error
    input.isErrorEnabled = error != null
}