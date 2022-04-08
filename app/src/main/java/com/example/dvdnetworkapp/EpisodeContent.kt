package com.example.dvdnetworkapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dvdnetworkapp.adapter.ButtonAdapter
import com.example.dvdnetworkapp.adapter.ContentAdapter
import com.example.dvdnetworkapp.model.FileInformation
import com.example.dvdnetworkapp.model.episodeModel

class EpisodeContent : AppCompatActivity() {

        private  lateinit var rcView: RecyclerView
        private lateinit var buttonArrayList: ArrayList<episodeModel>
        var buttonText = ArrayList<String>()
        var buttonCount = ArrayList<Int>()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_content)

        val contentName : TextView = findViewById(R.id.contentTitle)
        val contentImage : ImageView = findViewById(R.id.contentImage)
        val contentDes : TextView = findViewById(R.id.des)



        val bundle : Bundle?= intent.extras
        val name = bundle!!.getString("name")
        val image = bundle!!.getString("image")
        val episode = bundle!!.getInt("episode")
        val folder = bundle!!.getString("folder")
        val fileExtention = bundle!!.getString("fileExtention")
        val fileName = bundle!!.getString("fileName")
        val bannerImage = bundle!!.getString("bannerImage")
        val des = bundle!!.getString("des")

        Log.i(TAG, "My Int $episode")


        fun buttonInttoArray()

        {

            var x = 1

            while (x < (episode + 1))
            {
                buttonText.add(x.toString())
                buttonCount.add(x)
                x++
            }
            Log.i(TAG, "My Int $buttonText")
            Log.i(TAG, "My Int $buttonCount")

        }










        buttonInttoArray()
        contentName.text = name
        contentDes.text = des


        Glide.with(this)
            .load(bannerImage)
            .fitCenter()
            .into(contentImage)

        rcView = findViewById(R.id.buttonRC)
        rcView.layoutManager = GridLayoutManager(this, 5)
        rcView.setHasFixedSize(true)
        buttonArrayList = arrayListOf<episodeModel>()
        val episodeContent = ButtonAdapter(this, buttonArrayList)
        rcView.adapter = episodeContent
        getData()

        episodeContent.setOnButtonListener(object : ButtonAdapter.onButtonClickListener
        {
            override fun onButtonClick(position: Int) {
                val pos = position + 1

                val filepath = "smb://{IP.Address}/Media/Videos/$folder/$fileName$pos$fileExtention"
                println(filepath)
                val uri = Uri.parse(filepath) //your file URI

                val vlcIntent = Intent(Intent.ACTION_VIEW)
                vlcIntent.setPackage("org.videolan.vlc")
                vlcIntent.setDataAndTypeAndNormalize(uri, "video/*")
                startActivity(vlcIntent)
            }

        })


    }

    private fun getData() {
        for (i in buttonCount.indices)
        {
            val but = episodeModel(buttonText[i])
            buttonArrayList.add(but)
        }

    }




}