package com.song.httplibrary.data.json

import com.google.gson.Gson

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description : 解析并提供 Tab 数据
 */

// 定义 JSON 数据，包含 tabs 数组
val tapTabJson = """
{
  "tabs": [
    {
      "id": 1,
      "title": "视频"
    },
    {
      "id": 2,
      "title": "热图"
    }
  ]
}
""".trimIndent()

// TabItem 数据类，用于保存每个 Tab 的信息
data class TabItem(
    val id: Int,
    val title: String,
)

// 用于将整个 JSON 对象解析为包含 TabItem 列表的类型
data class TabsWrapper(
    val tabs: List<TabItem>
)

// 将 JSON 数据解析为 TabsWrapper 对象
val tabsWrapper: TabsWrapper = Gson().fromJson(tapTabJson, TabsWrapper::class.java)

// 提取其中的 tabs 列表
val tapTabData: List<TabItem> = tabsWrapper.tabs
