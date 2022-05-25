package com.example.alarmclock

import android.app.Application
import android.util.Log
import com.example.alarmclock.API.News.*
import com.example.alarmclock.Fragments.NewsFragment
import org.junit.Assert
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

abstract class NewsTest() {
    val testSource = Source("sourceId", "sourceName")
    val testArticles = Articles(testSource, "testAuthor", "testTitle", "testDesc", "testUrl", "testUrlToImage", "testPublish", "testContent")
    val articlesList = mutableListOf<Articles>(testArticles)
    val testNewsModel = NewsModel("testStatus", "testTotalResults", articlesList)

    abstract val testNewsFragment: NewsFragment

    @Test
    fun testNewsInformation(){
        testNewsFragment.displayInfo(testNewsModel)
        Assert.assertNotNull(testNewsFragment.getNewsInformation())
        Assert.assertTrue(true)
    }

}