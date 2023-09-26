package com.applemango.memodemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applemango.memodemo.databinding.ItemCalendarBinding


class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    inner class ViewHolder(private val bind : ItemCalendarBinding) : RecyclerView.ViewHolder(bind.root){
       // fun bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCalendarBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}