package com.kslimweb.kotlinyoutube

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    companion object {
        private const val HOMEFEED_JSON_URL = "https://api.letsbuildthatapp.com/youtube/home_feed"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_feed_view.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    private fun fetchJson() {
        val client = OkHttpClient()
        val request = Request.Builder().url(HOMEFEED_JSON_URL).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Log.e("Failed to execute")
                println("Failed to execute\"")
            }

            override fun onResponse(call: Call, response: Response) {

                val body =  response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                /**
                 * OkHttp's onResponse runs on background thread.
                 * If you want to immediately process something in the UI
                 * you will need to post to the main thread.
                 */
                runOnUiThread {
                    home_feed_view.adapter = HomeFeedAdapter(homeFeed)
                }
            }
        })
    }
}