package com.alan.githubsample.network.model

/**
 * Created by AlanChang on 2020/12/11.
 */
data class SearchUserResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<UserItems>?
)

data class UserItems(
    val login: String?,
    val id: Int?,
    val node_id: String?,
    val avatar_url: String?,
    val gravatar_id: String?,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val organizations_url: String?,
    val repos_url: String?,
    val events_url: String?,
    val received_events_url: String?,
    val type: String?,
    val site_admin: String?,
    val score: Int?
)