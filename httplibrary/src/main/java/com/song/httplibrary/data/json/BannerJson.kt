package com.song.httplibrary.data.json

import com.google.gson.Gson

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : 解析并提供 Banner 数据
 */

// 定义 JSON 数据，包含 banners 数组
val bannerJson = """
{
  "banners": [
    {
      "id": 1,
      "title": "Summer Sale",
      "imageUrl": "https://bbs-static.miyoushe.com/static/2024/07/15/2cff87c4ba5acd86144a76e39cf4a901_836302926695490472.png"
    },
    {
      "id": 2,
      "title": "New Arrivals",
      "imageUrl": "https://bbs-static.miyoushe.com/static/2024/03/18/d70f25b443df8029e05cd20a897126a6_7820117047572824878.png"
    },
    {
      "id": 3,
      "title": "Exclusive Offers",
      "imageUrl": "https://bbs-static.miyoushe.com/static/2023/11/03/060c8ba17ff372078dcf62d4d8b46d28_7229548510559384850.png"
    },
    {
      "id": 4,
      "title": "Exclusive Offers",
      "imageUrl": "https://upload-bbs.miyoushe.com/upload/2022/11/17/58da7a2a8695222efb9d42b750734b08_8280567314882516916.png"
    }
  ]
}
""".trimIndent()

data class BannerItem(
    val id: Int,
    val title: String,
    val imageUrl: String
)

data class BannerWrapper(
    val banners: List<BannerItem>
)

val bannerWrapper: BannerWrapper = Gson().fromJson(bannerJson, BannerWrapper::class.java)

// 获取 banners 列表
val banners: List<BannerItem> = bannerWrapper.banners
