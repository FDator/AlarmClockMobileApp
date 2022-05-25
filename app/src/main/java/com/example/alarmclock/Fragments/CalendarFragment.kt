package com.example.alarmclock.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import com.example.alarmclock.*
import com.example.alarmclock.Calendar.CalendarActivity
import com.example.alarmclock.Calendar.CalendarViewEventListActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_calendar, container, false)

        //get real time date
        val realDate: Calendar = Calendar.getInstance()
        val year: Int = realDate.get(Calendar.YEAR) //invokes get function and stores value
        val month: Int = realDate.get(Calendar.MONTH) + 1
        val dayOfMonth: Int = realDate.get(Calendar.DAY_OF_MONTH)

        val displayCurrentDay = root.findViewById<TextView>(R.id.calendar_activity_displayCurrentDate)
        displayCurrentDay.text = "Today's Date: " + "$year/$month/$dayOfMonth"

        //get date from userinput in calendarview
        val calendar = root.findViewById<CalendarView>(R.id.calendarView)
        calendar.setOnDateChangeListener(CalendarView.OnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val date = " " + year + "/" + (month + 1) + "/" + dayOfMonth
            val displaySelectedDay = root.findViewById<TextView>(R.id.calendar_activity_displaySelectedDate)
            displaySelectedDay.text = "Selected Date: " + date

            //switches to event creator activity
            val intent = Intent(context, CalendarActivity::class.java)
            intent.putExtra("calendarDate", date)
            startActivity(intent)
        })

        //switches to recyclerview for events list
        val eventViewButton = root.findViewById<FloatingActionButton>(R.id.calendar_view_events)
        eventViewButton.setOnClickListener() {
            val intentForEvents = Intent(context, CalendarViewEventListActivity::class.java)
            startActivity(intentForEvents)
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}



