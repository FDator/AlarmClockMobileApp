package com.example.alarmclock.API.News

data class NewsModel (
        val status: String? = null,
        val totalResults: String? = null,
        val articles: MutableList<Articles>? = null
    )

data class Articles(
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
)

data class Source(
    val id: String? = null,
    val name: String? = null
)