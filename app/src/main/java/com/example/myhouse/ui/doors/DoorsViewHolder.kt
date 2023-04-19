package com.example.myhouse.ui.doors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myhouse.R
import com.example.myhouse.domain.entity.DoorsModel

class DoorsViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val background: ImageView = view.findViewById(R.id.imageViewDoors)
    private val name: TextView = view.findViewById(R.id.doorsName)

    fun bind(door: DoorsModel) {
        background.load(door.snapshot)
        name.text = door.name
    }

}