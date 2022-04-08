package com.example.dvdnetworkapp.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dvdnetworkapp.R
import com.example.dvdnetworkapp.model.FileInformation
import com.example.dvdnetworkapp.model.episodeModel

class ButtonAdapter(val context: Context,private val buttonList: ArrayList<episodeModel>): RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    private lateinit var buttonListener: onButtonClickListener

    interface onButtonClickListener
    {
        fun onButtonClick(position: Int)
    }

    fun setOnButtonListener(listener: onButtonClickListener){
        buttonListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(context).inflate(R.layout.episode_selection, parent, false)
        return ViewHolder(adapterLayout, buttonListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = buttonList[position]
        holder.epButton.text = currentItem.episode
    }

    override fun getItemCount() = buttonList.size



    class ViewHolder(itemView: View, listener: onButtonClickListener) : RecyclerView.ViewHolder(itemView)
    {
        val epButton : Button = itemView.findViewById(R.id.episodeButton)

        init {
            epButton.setOnClickListener{
                listener.onButtonClick(adapterPosition)
            }


    }

    }


}