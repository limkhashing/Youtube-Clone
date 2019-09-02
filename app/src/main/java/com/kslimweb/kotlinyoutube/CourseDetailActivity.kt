package com.kslimweb.kotlinyoutube

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_course_detail.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        course_details.layoutManager = LinearLayoutManager(this)

        val naveBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = naveBarTitle

        val videoID = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)
        val courseDetailJsonURL =
            "https://api.letsbuildthatapp.com/youtube/course_detail?id=$videoID"

        fetchJson(courseDetailJsonURL)
    }

    private fun fetchJson(courseDetailJsonURL: String) {

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailJsonURL).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                //Log.e("Failed to execute")
                println("Failed to execute")
            }

            override fun onResponse(call: Call, response: Response) {
                val body =  response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val courseLessons = gson.fromJson(body, Array<CourseLessons>::class.java)

                runOnUiThread {
                    course_details.adapter = CourseDetailAdapter(courseLessons)
                }
            }
        })
    }
}
