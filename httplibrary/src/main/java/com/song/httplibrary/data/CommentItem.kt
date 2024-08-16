package com.song.httplibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024-08-15
 * @Description : 表示评论区中的一项，可以是评论或回复。
 *                实现了 Parcelable 接口，便于在 Android 组件之间传递数据。
 */
@Parcelize
data class CommentItem(
    /**
     * 用于区分评论类型的标识符：评论或回复。
     * @see CommentType
     */
    val type: CommentType,

    /**
     * 存储该项的具体数据。使用 @RawValue 注解允许存储任意类型的数据。
     */
    val data: @RawValue Any,

    /**
     * 评论的唯一标识符，用于区分不同的评论。
     */
    val commentId: String
) : Parcelable

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024-08-15
 * @Description : 定义评论区中项的类型。
 */
enum class CommentType {
    /** 评论类型 */
    COMMENT,

    /** 回复类型 */
    REPLY
}
