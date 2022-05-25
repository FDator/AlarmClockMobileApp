package com.example.alarmclock

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import com.example.alarmclock.Calendar.CalendarViewEventListActivity
import java.util.*

class AlarmActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var calendar: Calendar
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        createNotificationChannel()
        findViewById<Button>(R.id.fragment_createalarm_scheduleAlarm).setOnClickListener(){
            Log.d("FUCK", "I AM CLICKING THIS")
            setAlarm()
        }

    }

    private fun setAlarm() {
        var picker = findViewById<TimePicker>(R.id.fragment_createalarm_timePicker)

        calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY]= picker.hour
        calendar[Calendar.MINUTE]= picker.minute
        calendar[Calendar.SECOND]= 0
        calendar[Calendar.MILLISECOND]= 0

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            pendingIntent
        )

        Toast.makeText(this, "Alarm sucessfully set",Toast.LENGTH_SHORT).show()
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)

    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "AlarmAppChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("AlarmAndroid", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}