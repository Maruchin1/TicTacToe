package com.maruchin.tictactoe.presentation.framework

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class AppRecyclerViewAdapter<T>(
    private val context: Context,
    private val layout: Int
) : RecyclerView.Adapter<AppViewHolder>() {

    var items: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layout, parent, false)
        return AppViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}