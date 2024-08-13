package com.song.httplibrary.data

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : 代表一个视频的评论数据，包括视频 ID、评论列表和总评论数。
 */
data class VideoComments(
    val videoId: String,
    val comments: List<Comment>,
    val totalComments: Int
)

/**
 * @property commentId          评论的唯一标识符。
 * @property userId             用户的唯一标识符。
 * @property userName           用户名。
 * @property userAvatarUrl      用户头像的链接。
 * @property content            评论内容。
 * @property timestamp          评论的时间戳。
 * @property isExpanded         评论是否展开（显示所有回复）。
 * @property likes              评论的点赞数。
 * @property isLiked            当前用户是否已点赞此评论。
 * @property replies            评论下的回复列表。
 */
data class Comment(
    val commentId: String,
    val userId: String,
    val userName: String,
    val userAvatarUrl: String,
    val content: String,
    val timestamp: String,
    val isExpanded: Boolean,
    val likes: Int,
    val isLiked: Boolean,
    val replies: List<Reply>
)

/**
 * @property replyId            回复的唯一标识符。
 * @property userId             用户的唯一标识符。
 * @property userName           用户名。
 * @property userAvatarUrl      用户头像的链接。
 * @property content            回复内容。
 * @property timestamp          回复的时间戳。
 * @property likes              回复的点赞数。
 * @property isLiked            当前用户是否已点赞此回复。
 */
data class Reply(
    val replyId: String,
    val userId: String,
    val userName: String,
    val userAvatarUrl: String,
    val content: String,
    val timestamp: String,
    val likes: Int,
    val isLiked: Boolean
)