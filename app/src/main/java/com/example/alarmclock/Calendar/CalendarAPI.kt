package com.example.alarmclock.Calendar

import retrofit2.http.GET

interface CalendarAPI {
    @GET("/posts")
    fun getPosts(): retrofit2.Call<MutableList<PostModel>>
}