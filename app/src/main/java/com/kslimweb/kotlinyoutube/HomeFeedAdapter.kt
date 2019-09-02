package com.kslimweb.kotlinyoutube

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_feed_view_row.view.*

class HomeFeedAdapter(private val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val videoTitle = homeFeed.videos.get(position)
        holder.view.video_title?.text = videoTitle.name
        holder.view.channel_name?.text = videoTitle.channel.name + " - " + videoTitle.numberOfViews +
                " views\nSubscribers " + videoTitle.channel.numberOfSubscribers

        val thumbnailImage = holder.view.video_image
        val channelImage = holder.view.channel_image

        Picasso.get().load(videoTitle.imageUrl).into(thumbnailImage)
        Picasso.get().load(videoTitle.channel.profileImageUrl).into(channelImage)

        holder.video = videoTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.home_feed_view_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return homeFeed.videos.size
    }
}

class CustomViewHolder(val view: View, var video: Videos? = null): RecyclerView.ViewHolder(view) {

    companion object{
        const val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        const val VIDEO_ID_KEY = "VIDEO_ID"
    }

    init {
        view.setOnClickListener{
            val intent = Intent(view.context, CourseDetailActivity::class.java)
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)
            view.context.startActivity(intent)
        }
    }
}