package com.example.alarmclock.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.API.News.NewsAPIInterface
import com.example.alarmclock.API.News.NewsAdapter
import com.example.alarmclock.API.News.NewsModel
import com.example.alarmclock.API.News.NewsServiceGenerator
import com.example.alarmclock.R
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
public var newsInformation: NewsModel? = null

class NewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val button = root.findViewById<Button>(R.id.callNews)
        val newsServiceGenerator = NewsServiceGenerator.buildService(NewsAPIInterface::class.java)
        val call = newsServiceGenerator.getPosts()
        val newsRecyclerView = root.findViewById<RecyclerView>(R.id.NewsRecycler)

    //    button.setOnClickListener(){
    //        Log.e("Clicked", "Button Was Clicked")
        call.enqueue(object: retrofit2.Callback<NewsModel>{
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                if(response.isSuccessful){
                    displayInfo(response.body())
                    newsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = NewsAdapter(response.body()!!.articles){ position ->  onListItemClick(position, newsRecyclerView)}
                    }
                }
            }
            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.e("FailureReq", t.message.toString())
            }
        })
      //  }

        return root
    }

    private fun onListItemClick(position: Int, newsRecyclerView: RecyclerView) {
        //val title = newsRecyclerView.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<TextView>(R.id.NewsTitle)?.text.toString()
        Log.e("recycler view", "news item clicked #" + position)
        val url = newsInformation!!.articles!!.get(position).url.toString()
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    public fun displayInfo(info: NewsModel?){
        Log.e("Success Req","News Call Successful")
        Log.e("News Article", info.toString())
        Log.e("articles list size:", info!!.articles?.size.toString())

        newsInformation = info
    }

    public fun getNewsInformation(): NewsModel? {
        return newsInformation
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}