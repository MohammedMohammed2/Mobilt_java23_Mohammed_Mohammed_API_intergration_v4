package com.gritacademy.apiandroidapp

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import coil.compose.AsyncImage
import coil.load
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController

        findViewById<Button>(R.id.searchBtn).setOnClickListener {
            Log.i("sami", "onCreate: "+navController.currentDestination!!.toString())

            Log.i("sami", "onCreate: "+navController.currentDestination!!.toString()+navController.currentDestination!!.id)
            if(navController.currentDestination!!.id == R.id.blankFragment2)
                navController.navigate(R.id.action_blankFragment2_to_blankFragment22)
            else{
                Toast.makeText(this, "You are already in the desired fragment", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.locationBtn).setOnClickListener {
            Log.i("sami", "onCreate: "+navController.currentDestination!!.toString())

            Log.i("sami", "onCreate: "+navController.currentDestination!!.toString()+navController.currentDestination!!.id)
            if(navController.currentDestination!!.id == R.id.blankFragment22)
                navController.navigate(R.id.action_blankFragment22_to_blankFragment2)
            else{
                Toast.makeText(this, "You are already in the desired fragment", Toast.LENGTH_SHORT).show()
            }
        }
    }
}