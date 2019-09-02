package com.kslimweb.kotlinyoutube

// Modal object
// had to use val for publicly accessible
// the spelling of val is case sensitive, must same with JSON
class HomeFeed(val user: Users, val videos: List<Videos>)

class Videos(val id: Int, val name: String, val link: String,
             val imageUrl: String, val numberOfViews: Int, val channel: Channel)

class Users(val id: Int, val name: String, val userName: String)

class Channel(val name: String, val profileImageUrl: String, val numberOfSubscribers: Int)

class CourseLessons(val name: String, val duration: String, val number: Int,
                    val imageUrl: String, val link: String)