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
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var tvDesc:TextView
    lateinit var tvTemp:TextView
    lateinit var tvFeelsLike:TextView
    lateinit var tvHumidity:TextView
    lateinit var weatherIcon:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        tvDesc = findViewById(R.id.textView)
        tvTemp =findViewById(R.id.textView2)
        tvHumidity = findViewById(R.id.textView3)
        tvFeelsLike = findViewById(R.id.textView4)
        weatherIcon = findViewById(R.id.imageView)



        var rq: RequestQueue = Volley.newRequestQueue(this)
        var url = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=57706ebd416cbcfc748ac68986fa9a1f"
//        var request: ImageRequest  =ImageRequest(Request.Method.GET, "https://api.openweathermap.org/w/" + icon + ".png")
        var request: StringRequest = StringRequest (Request.Method.GET, url, { response ->

            Log.i("momo", "success" + response)
            val weatherArray:JSONArray = JSONObject(response).getJSONArray("weather")
            val jsnObjWeather:JSONObject = weatherArray.getJSONObject(0)
            var icon: String = jsnObjWeather.getString("icon")
            var description:String = jsnObjWeather.getString("description")
            val jsnObjMain = JSONObject(response).getJSONObject("main")
            val temp:Double = jsnObjMain.getDouble("temp")-273.15
            val feelsLike:Double = jsnObjMain.getDouble("feels_like")-273.15
            val humidity:Int = jsnObjMain.getInt("humidity")

            tvDesc.text = " weather: " + description
            tvTemp.text = " temp: " + temp.toInt().toString()
            tvFeelsLike.text =" feelsLike: " + feelsLike.toInt().toString()
            tvHumidity.text = " humidity: " + humidity.toInt().toString()
            Log.i("momo", "success" + description +temp+feelsLike+humidity)
        }, {
            Log.i("momo", "fail")

        })
        Log.i("momo", "class: ")
        rq.cache.clear()
        rq.add(request)
    }
}