package com.example.dvdnetworkapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dvdnetworkapp.R
import com.example.dvdnetworkapp.model.FileInformation

class ContentAdapter (val context: Context, private val contentList: ArrayList<FileInformation>): RecyclerView.Adapter<ContentAdapter.ViewHolder>()
{
    private lateinit var cardListener: onCardClickListener

    interface onCardClickListener
    {
        fun onCardClick(position: Int)
    }

    fun setOnCardListener(listener: onCardClickListener){
        cardListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.dvd_selection, parent, false)

        return ViewHolder(adapterLayout,cardListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = contentList[position]

        holder.cardName.text = currentItem.contentName
        Glide.with(this.context)
            .load(currentItem.contentImage)
            .fitCenter()
            .into(holder.cardImage)
    }

    override fun getItemCount() = contentList.size

    class ViewHolder(itemView: View, cardlistener: onCardClickListener) : RecyclerView.ViewHolder(itemView) {

        val cardName: TextView = itemView.findViewById(R.id.titleName)
        val cardImage: ImageView = itemView.findViewById(R.id.titleImage)

        init {
            itemView.setOnClickListener{
                cardlistener.onCardClick(adapterPosition)
            }

        }
    }


}