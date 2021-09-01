package com.example.anblickscodechallenge.view.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anblickscodechallenge.R

class RadioViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val channelImage: ImageView = view.findViewById(R.id.channel_image)
    val cTitle: TextView = view.findViewById(R.id.channel_title)
    val cDescription: TextView = view.findViewById(R.id.channel_description)
    val djName: TextView = view.findViewById(R.id.dj_name)

    fun bindData(context: Context, largeimage: String?, title: String, description: String, dj: String) {
        largeimage?.let {
            Glide.with(context)
                .load(largeimage)
                .into(channelImage)
        }
        cTitle.text = title
        cDescription.text = description
        djName.text = dj
        }
    }
