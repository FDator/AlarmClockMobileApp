package com.example.alarmclock.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alarmclock.R
import android.content.Intent
import android.widget.Button
import com.example.alarmclock.Calendar.CalendarViewEventListActivity
import com.example.alarmclock.Weather.WeatherActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_weather, container, false)

        val weatherButton = root.findViewById<Button>(R.id.weatherbutton)
        weatherButton.setOnClickListener() {
            val goWeatherActivity = Intent(context, WeatherActivity::class.java)
            startActivity(goWeatherActivity)
        }
        return root
            }
        }





    
