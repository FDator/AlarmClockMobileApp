package com.example.alarmclock.API.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.R
import com.squareup.picasso.Picasso


class NewsAdapter(val newsModelArticles: MutableList<Articles>?, private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card_post, parent, false)
        return NewsViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.bindView(newsModelArticles!![position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return newsModelArticles?.size!!
    }
}


class NewsViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit): RecyclerView.ViewHolder(itemView), View.OnClickListener{
    private val newsTitle: TextView = itemView.findViewById(R.id.NewsTitle)
    private val newsDesc: TextView = itemView.findViewById(R.id.NewsDesc)
    private val newsImage: ImageView = itemView.findViewById(R.id.NewsImage)


    init {
        itemView.setOnClickListener(this)
    }

    fun bindView(newsModelArticles: Articles, onItemClicked: (position: Int) -> Unit){
        newsTitle.text = newsModelArticles.title
        newsDesc.text = newsModelArticles.description

        Picasso.get()
            .load(newsModelArticles.urlToImage.toString())
            .into(newsImage)
    }

    override fun onClick(v: View) {
        val position = adapterPosition
        onItemClicked(position)
    }

}