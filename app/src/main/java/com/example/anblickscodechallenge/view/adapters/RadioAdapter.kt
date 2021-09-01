package com.example.anblickscodechallenge.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anblickscodechallenge.R
import com.example.anblickscodechallenge.model.Channel

class RadioAdapter(private val onClick: OnChannelClick): RecyclerView.Adapter<RadioViewHolder>() {

    interface OnChannelClick{
        fun openDetails(channel:Channel)
    }
    var displayList: List<Channel> = listOf()

    var channels: List<Channel> = listOf()
    set(value) {
        field = value
        displayList = value
        notifyDataSetChanged()
    }

    fun search(djName: String){
        displayList = if(djName.trim().isEmpty())
            channels
        else
            channels.filter { it.dj.lowercase().startsWith(djName.lowercase())}
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.channel_item, parent, false)
        return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val currentChannel = displayList[position]
        holder.bindData(holder.itemView.context,
            currentChannel.largeimage,
            currentChannel.title,
            currentChannel.description,
            currentChannel.dj)

        holder.itemView.setOnClickListener{
            onClick.openDetails(currentChannel)
        }
    }

    override fun getItemCount(): Int {
        return displayList.size
    }
}