package com.kslimweb.kotlinyoutube

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kslimweb.kotlinyoutube.CourseLessonViewHolder.Companion.COURSE_LESSON_LINK_KEY
import kotlinx.android.synthetic.main.activity_course_lesson.*

class CourseLessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_lesson)

        val courseLink = intent.getStringExtra(COURSE_LESSON_LINK_KEY)

        course_lesson_webview.settings.javaScriptEnabled = true
        course_lesson_webview.settings.loadWithOverviewMode = true
        course_lesson_webview.settings.useWideViewPort = true

        course_lesson_webview.loadUrl(courseLink)
    }
}
