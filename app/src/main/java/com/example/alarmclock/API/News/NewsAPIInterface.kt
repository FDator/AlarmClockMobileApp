package com.example.alarmclock.API.News

import retrofit2.Call
import retrofit2.http.GET

interface NewsAPIInterface {
    //API KEY = 98c75cad4c864c628e7c25b7d4766607
    ///top-headlines/sources?apiKey=98c75cad4c864c628e7c25b7d4766607
    @GET("top-headlines?sources=techcrunch&apiKey=98c75cad4c864c628e7c25b7d4766607")
    fun getPosts(): Call<NewsModel>

}