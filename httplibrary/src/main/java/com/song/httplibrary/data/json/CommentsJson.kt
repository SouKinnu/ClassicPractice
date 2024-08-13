package com.song.httplibrary.data.json

import com.google.gson.Gson
import com.song.httplibrary.data.VideoComments

val commentsJson = """
{
  "videoId": "video123456",
  "comments": [
    {
      "commentId": "comment001",
      "userId": "user001",
      "userName": "小明",
      "userAvatarUrl": "https://example.com/avatar_001.png",
      "content": "这个视频真有趣！",
      "timestamp": "2024-08-11T10:30:00Z",
      "isExpanded": false,
      "likes": 10,
      "isLiked": true,
      "replies": [
        {
          "replyId": "reply001",
          "userId": "user002",
          "userName": "小红",
          "userAvatarUrl": "https://example.com/avatar_002.png",
          "content": "我也觉得很好笑！",
          "timestamp": "2024-08-11T10:31:00Z",
          "likes": 5,
          "isLiked": false
        },
        {
          "replyId": "reply002",
          "userId": "user003",
          "userName": "小李",
          "userAvatarUrl": "https://example.com/avatar_003.png",
          "content": "真的，我笑了好久！",
          "timestamp": "2024-08-11T10:32:00Z",
          "likes": 3,
          "isLiked": true
        }
      ]
    },
    {
      "commentId": "comment002",
      "userId": "user004",
      "userName": "小张",
      "userAvatarUrl": "https://example.com/avatar_004.png",
      "content": "真是个神作！",
      "timestamp": "2024-08-11T10:33:00Z",
      "isExpanded": false,
      "likes": 20,
      "isLiked": false,
      "replies": []
    },
    {
      "commentId": "comment003",
      "userId": "user005",
      "userName": "小华",
      "userAvatarUrl": "https://example.com/avatar_005.png",
      "content": "再来一波！",
      "timestamp": "2024-08-11T10:34:00Z",
      "isExpanded": false,
      "likes": 8,
      "isLiked": true,
      "replies": [
        {
          "replyId": "reply003",
          "userId": "user006",
          "userName": "小丽",
          "userAvatarUrl": "https://example.com/avatar_006.png",
          "content": "支持！",
          "timestamp": "2024-08-11T10:35:00Z",
          "likes": 1,
          "isLiked": false
        }
      ]
    },
    {
      "commentId": "comment004",
      "userId": "user007",
      "userName": "小强",
      "userAvatarUrl": "https://example.com/avatar_007.png",
      "content": "感觉这段剪辑得很好！",
      "timestamp": "2024-08-11T10:36:00Z",
      "isExpanded": false,
      "likes": 15,
      "isLiked": false,
      "replies": [
        {
          "replyId": "reply004",
          "userId": "user008",
          "userName": "小芳",
          "userAvatarUrl": "https://example.com/avatar_008.png",
          "content": "没错，剪辑师很棒！",
          "timestamp": "2024-08-11T10:37:00Z",
          "likes": 4,
          "isLiked": true
        }
      ]
    }
  ],
  "totalComments": 4
}
""".trimIndent()
val commentsData: VideoComments = Gson().fromJson(commentsJson, VideoComments::class.java)
