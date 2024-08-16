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
      "userAvatarUrl": "https://bbs-static.miyoushe.com/static/2024/07/15/2cff87c4ba5acd86144a76e39cf4a901_836302926695490472.png",
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
          "userAvatarUrl": "https://bbs-static.miyoushe.com/static/2024/03/18/d70f25b443df8029e05cd20a897126a6_7820117047572824878.png",
          "content": "我也觉得很好笑！",
          "timestamp": "2024-08-11T10:31:00Z",
          "likes": 5,
          "isLiked": false
        },
        {
          "replyId": "reply002",
          "userId": "user003",
          "userName": "小李",
          "userAvatarUrl": "https://bbs-static.miyoushe.com/static/2023/11/03/060c8ba17ff372078dcf62d4d8b46d28_7229548510559384850.png",
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
      "userAvatarUrl": "https://upload-bbs.miyoushe.com/upload/2022/11/17/58da7a2a8695222efb9d42b750734b08_8280567314882516916.png",
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
      "userAvatarUrl": "https://bbs-static.miyoushe.com/static/2024/07/15/2cff87c4ba5acd86144a76e39cf4a901_836302926695490472.png",
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
          "userAvatarUrl": "https://bbs-static.miyoushe.com/static/2024/03/18/d70f25b443df8029e05cd20a897126a6_7820117047572824878.png",
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
      "userAvatarUrl": "https://bbs-static.miyoushe.com/static/2023/11/03/060c8ba17ff372078dcf62d4d8b46d28_7229548510559384850.png",
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
          "userAvatarUrl": "https://upload-bbs.miyoushe.com/upload/2022/11/17/58da7a2a8695222efb9d42b750734b08_8280567314882516916.png",
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
