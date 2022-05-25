package com.example.alarmclock.Calendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.R
import com.google.firebase.firestore.FirebaseFirestore


class CalendarViewEventListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendarvieweventlist)

        val recyclerView = findViewById<RecyclerView>(R.id.calender_view_recycler_list)

        //get instance of database
        val db = FirebaseFirestore.getInstance()
        val calendarCollection = db.collection("calendar")
        val list = mutableListOf<PostModel>()

        calendarCollection.get()
            .addOnSuccessListener { result ->
                result.forEach {
                    Log.d("calendar Collection: ", it.data["calendar"].toString())
                    val entry = PostModel(it.data["calendarDate"].toString(),
                                          it.data["Subject"].toString(),
                                          it.data["Description"].toString(),
                                          it.data["Location"].toString())

                    list.add(entry)
                }
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = PostAdapter(list){position -> onListItemClick(position, recyclerView)}

                }
            }
    }

    fun onListItemClick(position: Int, rView: RecyclerView){
        Log.d("Entry Position", position.toString())
        Log.d("Entry Info", rView.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<TextView>(R.id.eCalendar)?.text.toString())

    }
}