package com.song.httplibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * @Author      : SongJin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/20 13:53.
 * @Description :描述
 */
@Parcelize
data class ListItem(val type: ListType, val data: @RawValue Any) : Parcelable

enum class ListType {
    TITLE, CONTENT
}

