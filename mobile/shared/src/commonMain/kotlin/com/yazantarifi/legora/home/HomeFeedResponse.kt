package com.yazantarifi.legora.home

import com.yazantarifi.legora.response.LegoraMatch
import com.yazantarifi.legora.response.LegoraTftMatch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeFeedResponse(
    @SerialName("type") val type: String? = "",
    @SerialName("accountHash") val accountHash: String? = "",
    @SerialName("summonerHighlightName") val summonerHighlightName: String? = "",
    @SerialName("serverHighlightName") val serverHighlightName: String? = "",
    @SerialName("profileImage") val profileImage: String? = "",
    @SerialName("items") val items: List<HomeWidgetItem>? = null,
    @SerialName("lolMatches") val lolMatches: List<LegoraMatch>? = null,
    @SerialName("tftMatches") val tftMatches: List<LegoraTftMatch>? = null,
    @SerialName("tips") val tips: List<GameTip>? = null,
)

@Serializable
data class GameTip(
    @SerialName("game_key") val gameKey: String? = "",
    @SerialName("game_name") val gameName: String? = "",
    @SerialName("tip_title") val tipTitle: String? = "",
    @SerialName("tip_description") val tipDescription: String? = "",
    @SerialName("video_link") val videoLink: String? = "",
    @SerialName("difficulty") val difficulty: String? = "",
    @SerialName("likes_count") val likes: Int? = 0,
    @SerialName("comments_count") val comments: Int? = 0,
    @SerialName("created_timestamp") val createdTimestamp: Long? = 0L,
)

@Serializable
data class HomeWidgetItem(
    @SerialName("game_key") val gameKey: String? = "",
    @SerialName("game_name") val gameName: String? = "",
    @SerialName("event_image") val gameImage: String? = "",
    @SerialName("image") val image: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("youtube_link") val youtubeLink: String? = "",
    @SerialName("twitch_link") val twitchLink: String? = "",
    @SerialName("twitter_link") val twitterLink: String? = "",
    @SerialName("rank_name") val rankName: String? = "",
    @SerialName("event_description") val description: String? = "",
    @SerialName("event_link") val link: String? = "",
    @SerialName("event_created_timestamp") val timestamp: String? = "",
    @SerialName("is_video") val isVideo: Boolean? = false,
    @SerialName("comments_count") val commentsCount: Int? = 0,
    @SerialName("likes_count") val likesCount: Int? = 0,
)