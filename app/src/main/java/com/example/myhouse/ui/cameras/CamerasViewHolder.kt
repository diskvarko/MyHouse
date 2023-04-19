package com.example.myhouse.ui.cameras

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myhouse.R
import com.example.myhouse.domain.entity.CameraModel
import com.example.myhouse.domain.entity.DoorsModel

class CamerasViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val background: ImageView = view.findViewById(R.id.imageViewCameras)
    private val name: TextView = view.findViewById(R.id.cameraNameTextView)
    private val room: TextView = view.findViewById(R.id.roomNameTextView)
    private val isRec: ImageButton = view.findViewById(R.id.recImageView)
    private val isFav: ImageButton = view.findViewById(R.id.favoriteImageView)


    fun bind(camera: CameraModel) {
        background.load(camera.snapshot)
        name.text = camera.name
        if (!camera.room.isNullOrEmpty()) {
            room.visibility = View.VISIBLE
            room.text = camera.room
        }
        isRec.isVisible = camera.rec
        isFav.isVisible = camera.favorites
    }

}