package com.example.alarmclock.API.News

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsServiceGenerator {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        //API KEY = 98c75cad4c864c628e7c25b7d4766607
            //https://newsapi.org/v2/everything?q=tesla&from=2022-04-05&sortBy=publishedAt&apiKey=98c75cad4c864c628e7c25b7d4766607
            //https://jsonplaceholder.typicode.com/todos/

        //.baseUrl("https://newsapi.org/")
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T>buildService(service: Class<T>): T {
        Log.d("BUILD","HERE")
        return retrofit.create(service)
    }
}