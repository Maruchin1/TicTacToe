package com.maruchin.tictactoe.presentation.framework

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.maruchin.tictactoe.R
import com.maruchin.tictactoe.core.entities.PlayerMarker

@BindingAdapter("inputError")
fun inputError(input: TextInputLayout, error: String?) {
    input.error = error
    input.isErrorEnabled = error != null
}

@BindingAdapter("android:src")
fun srcPlayerMarker(imageView: ImageView, marker: PlayerMarker) {
    val resId = when (marker) {
        PlayerMarker.NONE -> 0
        PlayerMarker.CIRCLE -> R.drawable.ic_panorama_fish_eye_black_24dp
        PlayerMarker.CROSS -> R.drawable.ic_close_black_24dp
    }
    imageView.setImageResource(resId)
}

@BindingAdapter("android:visibility")
fun visibility(view: View, visible: Boolean) {
    view.visibility = when {
        visible -> View.VISIBLE
        else -> View.INVISIBLE
    }
}