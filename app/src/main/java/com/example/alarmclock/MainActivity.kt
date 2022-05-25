package com.example.alarmclock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.alarmclock.Fragments.AlarmFragment
import com.example.alarmclock.Fragments.CalendarFragment
import com.example.alarmclock.Fragments.NewsFragment
import com.example.alarmclock.Fragments.WeatherFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val alarmfragment = AlarmFragment()
    private val weatherfragment = WeatherFragment()
    private val calendarfragment = CalendarFragment()
    private val newsfragment = NewsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(alarmfragment)

        findViewById<BottomNavigationView>(R.id.bottom_nav).setOnNavigationItemSelectedListener {
            item -> when(item.itemId){
                R.id.ic_alarm -> replaceFragment(alarmfragment)
                R.id.ic_weather -> replaceFragment(weatherfragment)
                R.id.ic_calendar -> replaceFragment(calendarfragment)
                R.id.ic_news -> replaceFragment(newsfragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}