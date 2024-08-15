package com.song.httplibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/20 13:53
 * @Description : 用于表示列表中的项，可以是标题或内容。实现了 Parcelable 接口以便于在 Android 的组件之间传递数据。
 */
@Parcelize
data class ListItem(
    /**
     * 列表项的类型，用于区分项的类别。
     * @see ListType
     */
    val type: ListType,

    /**
     * 存储项的具体数据。使用 @RawValue 注解允许存储任意类型的数据。
     */
    val data: @RawValue Any
) : Parcelable

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024/5/20 13:53
 * @Description : 定义列表项的类型。
 */
enum class ListType {
    /** 标题类型项 */
    TITLE,

    /** 内容类型项 */
    CONTENT
}

