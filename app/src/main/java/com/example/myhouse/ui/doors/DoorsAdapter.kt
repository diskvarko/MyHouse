package com.example.myhouse.ui.doors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhouse.R
import com.example.myhouse.domain.entity.DoorsModel

class DoorsAdapter() :
    RecyclerView.Adapter<DoorsViewHolder>() {

    private var doorsList: List<DoorsModel> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorsViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val doorView = inflater.inflate(R.layout.view_holder_doors_item, parent, false)
        return DoorsViewHolder(doorView)
    }

    override fun getItemCount(): Int {
        return doorsList.size
    }

    fun setDoors(newDoors: List<DoorsModel>) {
        doorsList = newDoors
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DoorsViewHolder, position: Int) {
        holder.bind(doorsList[position])
    }
}