package com.example.alarmclock.Calendar

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.alarmclock.MainActivity
import com.example.alarmclock.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class CalendarActivity: AppCompatActivity() {

    private lateinit var subject: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var location: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        subject = findViewById(R.id.calendar_input1)
        description = findViewById(R.id.calendar_input2)
        location = findViewById(R.id.calendar_input3)

        val sourceIntent = getIntent()
        val calendarDate = sourceIntent.getStringExtra("calendarDate").toString()

        val textViewDisplayDate = findViewById<TextView>(R.id.activity_calendar_display_date)
        textViewDisplayDate.text = "Event Date: " + calendarDate

        val button = findViewById<Button>(R.id.save_event_button)
        button.setOnClickListener() {

            val scheduleSubject =
                findViewById<TextInputEditText>(R.id.calendar_input1).text.toString()
            val scheduleDescription =
                findViewById<TextInputEditText>(R.id.calendar_input2).text.toString()
            val scheduleLocation =
                findViewById<TextInputEditText>(R.id.calendar_input3).text.toString()

            val db = FirebaseFirestore.getInstance()
            val entry: MutableMap<String, Any> = HashMap()

                entry["calendarDate"] = calendarDate
                entry["Subject"] = scheduleSubject
                entry["Description"] = scheduleDescription
                entry["Location"] = scheduleLocation

                db.collection("calendar").document()
                    .set(entry)
                    .addOnSuccessListener {
                        Log.d("db addEntry", "Sucessfully Added to Database")
                    }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "Successfully Saved Information", Toast.LENGTH_LONG).show()
        }

    }
}