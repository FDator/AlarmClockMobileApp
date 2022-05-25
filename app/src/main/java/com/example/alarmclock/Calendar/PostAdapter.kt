package com.example.alarmclock.Calendar

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.R
import javax.security.auth.Subject

class PostAdapter(val postModel: MutableList<PostModel>, val onItemClicked:(position: Int) -> Unit):
    RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_docentry, parent, false)
        return PostViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(postModel[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return postModel.size
    }
}

class PostViewHolder(itemView: View, val onItemClicked:(position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
    private val calendarDate: TextView = itemView.findViewById(R.id.eCalendar)
    private val Subject: TextView = itemView.findViewById(R.id.eSubject)
    private val Description: TextView = itemView.findViewById(R.id.eDescription)
    private val Location: TextView = itemView.findViewById(R.id.eLocation)

    fun bindView(postModel: PostModel, onItemClicked: (position: Int) -> Unit){
        calendarDate.text = postModel.calendarDate
        Subject.text = postModel.Subject
        Description.text = postModel.Description
        Location.text = postModel.Location
    }

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val position = adapterPosition
        onItemClicked(position)
    }
}