package com.song.httplibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024-08-15
 * @Description : 表示米游社数据的模型类，包含了一组头像和相关信息。实现了 Parcelable 接口以便于在 Android 的组件之间传递数据。
 */
@Parcelize
data class MiYSheData(
    /*** 图标的 URL 地址。*/
    val icon: String,

    /*** 唯一标识符。*/
    val id: Int,

    /*** 包含多个头像的列表。*/
    val list: List<Avatar>,

    /*** 名称。*/
    val name: String,

    /*** 数量。*/
    val num: Int,

    /*** 排序顺序。*/
    val sort_order: Int,

    /*** 状态信息。*/
    val status: String,

    /*** 类型信息。*/
    val type: String
) : Parcelable

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024-08-15
 * @Description : 表示头像的模型类，包含了头像的详细信息。实现了 Parcelable 接口以便于在 Android 的组件之间传递数据。
 */
@Parcelize
data class Avatar(
    /*** 图标的 URL 地址。*/
    val icon: String,

    /*** 唯一标识符。*/
    val id: Int,

    /*** 名称。*/
    val name: String,

    /*** 排序顺序。*/
    val sort_order: Int,

    /*** 状态信息。*/
    val status: String
) : Parcelable