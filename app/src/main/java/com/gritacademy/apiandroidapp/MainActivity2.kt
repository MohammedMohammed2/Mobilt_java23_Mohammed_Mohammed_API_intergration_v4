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
    lateinit var tvDesc:TextView
    lateinit var tvTemp:TextView
    lateinit var tvFeelsLike:TextView
    lateinit var tvHumidity:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        tvDesc = findViewById(R.id.textView)
        tvTemp =findViewById(R.id.textView2)
        tvHumidity = findViewById(R.id.textView3)
        tvFeelsLike = findViewById(R.id.textView4)
        var weatherIcon:ImageView = findViewById(R.id.imageView)



        var rq: RequestQueue = Volley.newRequestQueue(this)
        var url = "https://api.weatherapi.com/v1/current.json?key=0e61e15f7f7e44b797a93849241209&q=malmö&aqi=no"

        var request: StringRequest = StringRequest (Request.Method.GET, url, { response ->

            Log.i("momo", "success" + response)
            val weatherArray:JSONObject = JSONObject(response).getJSONObject("current")
            val temp:String = weatherArray.getString("temp_c")
            Log.i("momo", "onCreate: " + temp + weatherArray)
            val jsnObjMain:JSONObject = weatherArray.getJSONObject("condition")
            val text:String = jsnObjMain.getString("text")
            val icon:String = jsnObjMain.getString("icon")
            val humidity:String = weatherArray.getString("humidity")

             tvDesc.text = " weather: " + text
             tvTemp.text = " temp: " + temp
             tvHumidity.text = " humidity: " + humidity + "%"
             Glide.with(this).load("https:" + icon).into(weatherIcon)



        }, {
            Log.i("momo", "fail")

        })
        Log.i("momo", "class: ")

        rq.add(request)
        rq.cache.clear()
    }
}