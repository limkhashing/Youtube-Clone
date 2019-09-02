package com.kslimweb.kotlinyoutube

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.course_detail_row.view.*

class CourseDetailAdapter(private val courseLessons: Array<CourseLessons>) : RecyclerView.Adapter<CourseLessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseLessonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val customView = layoutInflater.inflate(R.layout.course_detail_row, parent, false)
        return CourseLessonViewHolder(customView)
    }

    override fun getItemCount(): Int {
        return courseLessons.size
    }

    override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {

        val courseLesson = courseLessons.get(position)
        holder.view.course_lesson_title?.text = courseLesson.name
        holder.view.duration?.text = "Episode #" + position.inc() + "\n" + "Duration: " + courseLesson.duration

        val courseDetailImage = holder.view.course_detail_image
        Picasso.get().load(courseLesson.imageUrl).into(courseDetailImage)

        holder.courseLesson = courseLesson
    }

}

class CourseLessonViewHolder(val view : View, var courseLesson: CourseLessons? = null): RecyclerView.ViewHolder(view) {

    companion object {
        var COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK_KEY"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CourseLessonActivity::class.java)

            intent.putExtra(COURSE_LESSON_LINK_KEY, courseLesson?.link)
            view.context.startActivity(intent)
        }
    }
}