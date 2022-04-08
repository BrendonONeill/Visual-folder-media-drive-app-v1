package com.example.dvdnetworkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dvdnetworkapp.adapter.ContentAdapter
import com.example.dvdnetworkapp.model.FileInformation
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {


    private lateinit var newRecyclerview: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dvdsList: ArrayList<FileInformation> = ArrayList()


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



                // Now add all the variables to the data model class and the data model class to the array list.
                val dvdInformation =
                    FileInformation(id, name, image, episodes, folder, filename, fileExtention)

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
        val cardAdapter = ContentAdapter(this, dvdsList)

        newRecyclerview.adapter = cardAdapter

        cardAdapter.setOnCardListener(object : ContentAdapter.onCardClickListener {
            override fun onCardClick(position: Int) {
                val intent = Intent(this@MainActivity,EpisodeContent::class.java)
                intent.putExtra("name", dvdsList.get(position).contentName)
                intent.putExtra("image", dvdsList.get(position).contentImage)
                intent.putExtra("episode", dvdsList.get(position).episodes)
                intent.putExtra("folder", dvdsList.get(position).folder)
                intent.putExtra("fileName", dvdsList.get(position).fileName)
                intent.putExtra("fileExtention", dvdsList.get(position).fileExtention)

                startActivity(intent)
            }

        })

        }



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


}