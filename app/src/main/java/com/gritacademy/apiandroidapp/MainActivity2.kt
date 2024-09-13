package com.gritacademy.apiandroidapp

import android.app.FragmentTransaction
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.BackStackEntry
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

    lateinit var changeFragBtn:Button
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController

        changeFragBtn = findViewById(R.id.searchBtn)

        changeFragBtn.setOnClickListener {
            if (changeFragBtn.text == "search") {
                changeFragBtn.text = "malmö"
                supportFragmentManager.beginTransaction().addToBackStack("1")
                    .replace(R.id.fragmentContainerView2, BlankFragment2()).commit()
            }
            else{
                    changeFragBtn.text = "search"
                    supportFragmentManager.beginTransaction().addToBackStack("2").replace(R.id.fragmentContainerView2,BlankFragment()).commit()
                }
            }
        }
    }