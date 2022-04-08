package com.example.dvdnetworkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dvdnetworkapp.adapter.ContentAdapter
import com.example.dvdnetworkapp.model.FileInformation
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    private lateinit var newRecyclerview: RecyclerView
    private lateinit var tempRC: RecyclerView
    private val dvdsList: ArrayList<FileInformation> = ArrayList()
    private val tempList: ArrayList<FileInformation> = ArrayList()
    private lateinit var cardAdapter: ContentAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navbar = findViewById<BottomNavigationView>(R.id.bottomnavbardesign)
        val searchInput = findViewById<SearchView>(R.id.searchbar)

    //  this is the bottom navigation bar that allows a user to filter or search objects within the recyclier view
        navbar.setOnNavigationItemSelectedListener{
            when(it.itemId)
            {
                R.id.menuAll -> allFiles()
                R.id.menuFilm -> movieFiles()
                R.id.menuTv -> tvFiles()
                R.id.menuSearch -> searchInput.visibility
                else -> {}
            }
            true
        }




        try {
            // As we have JSON object, so we are getting the object
            //Here we are calling a Method which is returning the JSON object
            val dvdObject = JSONObject(getJSONFromAssets()!!)
            // fetch JSONArray named users by using getJSONArray
            val dvdsArray = dvdObject.getJSONArray("dvdInformation")
            // Get the users data using for loop i.e. id, name, email and so on

            for (i in 0 until dvdsArray.length()) {
                // Create a JSONObject for fetching single User's Data
                val dvd = dvdsArray.getJSONObject(i)
                // Fetch id store it in variable
                val id = dvd.getInt("id")
                val name = dvd.getString("contentName")
                val image = dvd.getString("image")
                val episodes = dvd.getInt("episodes")
                val folder = dvd.getString("folder")
                val filename = dvd.getString("filename")
                val fileExtention = dvd.getString("fileextention")
                val bannerImage = dvd.getString("bannerImage")
                val des = dvd.getString("des")



                // Now add all the variables to the data model class and the data model class to the array list.
                val dvdInformation =
                    FileInformation(id, name, image, episodes, folder, filename, fileExtention, bannerImage, des)

                // add the details in the list
                dvdsList.add(dvdInformation)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }



        newRecyclerview = findViewById(R.id.rcContent)
        newRecyclerview.layoutManager = GridLayoutManager(this, 3)
        newRecyclerview.setHasFixedSize(true)
        tempList.addAll(dvdsList)
        cardAdapter = ContentAdapter(this, dvdsList)
        addCardListener()
        createRecycler()






        }

    //This is where the information is taken from the json file and placed the objects into an Array

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myDvdJSONFile = assets.open("Dvd.json")
            val size = myDvdJSONFile.available()
            val buffer = ByteArray(size)
            myDvdJSONFile.read(buffer)
            myDvdJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun createRecycler()
    {
        newRecyclerview.adapter = cardAdapter
    }

    private fun addCardListener()
    {

        // This is where the information of the object is intented to the new activity to display the right information for each object within the array

        cardAdapter.setOnCardListener(object : ContentAdapter.onCardClickListener {
            override fun onCardClick(position: Int) {
                val intent = Intent(this@MainActivity,EpisodeContent::class.java)
                intent.putExtra("name", dvdsList.get(position).contentName)
                intent.putExtra("image", dvdsList.get(position).contentImage)
                intent.putExtra("episode", dvdsList.get(position).episodes)
                intent.putExtra("folder", dvdsList.get(position).folder)
                intent.putExtra("fileName", dvdsList.get(position).fileName)
                intent.putExtra("fileExtention", dvdsList.get(position).fileExtention)
                intent.putExtra("bannerImage", dvdsList.get(position).bannerImage)
                intent.putExtra("des", dvdsList.get(position).des)

                startActivity(intent)
            }

        })
    }







    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun allFiles()
    {
        dvdsList.clear()
        dvdsList.addAll(tempList)
        addCardListener()
        cardAdapter.notifyDataSetChanged()

    }
    private fun tvFiles()
    {
        dvdsList.clear()
        for(x in tempList)
        {
            if(x.episodes > 1)
            {
                dvdsList.add(x)
            }
        }
        addCardListener()
        cardAdapter.notifyDataSetChanged()
    }
    private fun movieFiles()
    {
        dvdsList.clear()
        for(x in tempList)
        {
            if(x.episodes == 1)
            {
                dvdsList.add(x)
            }
        }
        addCardListener()
        cardAdapter.notifyDataSetChanged()
    }
    private fun searchFiles()
    {

    }


}