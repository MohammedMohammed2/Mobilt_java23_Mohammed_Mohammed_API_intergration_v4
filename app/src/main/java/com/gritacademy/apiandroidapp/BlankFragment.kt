package com.gritacademy.apiandroidapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class BlankFragment : Fragment(R.layout.fragment_blank) {
    lateinit var tvDesc: TextView
    lateinit var tvTemp: TextView
    lateinit var tvFeelsLike: TextView
    lateinit var tvHumidity: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvDesc = view.findViewById(R.id.textView9)
        tvTemp =view.findViewById(R.id.textView7)
        tvHumidity = view.findViewById(R.id.textView8)
        var weatherIcon: ImageView = view.findViewById(R.id.imageView3)

        var rq: RequestQueue = Volley.newRequestQueue(context)
        var url = "https://api.weatherapi.com/v1/current.json?key=0e61e15f7f7e44b797a93849241209&q=malmö&aqi=no"

        var request: StringRequest = StringRequest (Request.Method.GET, url, { response ->

            Log.i("momo", "success" + response)
            val weatherArray: JSONObject = JSONObject(response).getJSONObject("current")
            val temp:String = weatherArray.getString("temp_c")
            Log.i("momo", "onCreate: " + temp + weatherArray)
            val jsnObjMain: JSONObject = weatherArray.getJSONObject("condition")
            val text:String = jsnObjMain.getString("text")
            val icon:String = jsnObjMain.getString("icon")
            val humidity:String = weatherArray.getString("humidity")

            tvDesc.text = " weather: " + text
            tvTemp.text = " temp: " + temp +"°c"
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