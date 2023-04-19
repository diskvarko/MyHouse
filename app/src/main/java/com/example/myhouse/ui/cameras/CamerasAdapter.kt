package com.example.myhouse.ui.cameras

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhouse.R
import com.example.myhouse.domain.entity.CameraModel

class CamerasAdapter() : RecyclerView.Adapter<CamerasViewHolder>() {

    private var camerasList: List<CameraModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamerasViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val doorView = inflater.inflate(R.layout.view_holder_cameras_item, parent, false)
        return CamerasViewHolder(doorView)
    }

    override fun getItemCount(): Int {
        return camerasList.size
    }

    fun setList(newDoors: List<CameraModel>) {
        camerasList = newDoors
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CamerasViewHolder, position: Int) {
        holder.bind(camerasList[position])
    }
}