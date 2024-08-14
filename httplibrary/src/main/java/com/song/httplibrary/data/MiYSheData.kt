package com.song.httplibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiYSheData(
    val icon: String,
    val id: Int,
    val list: List<Avatar>,
    val name: String,
    val num: Int,
    val sort_order: Int,
    val status: String,
    val type: String
) : Parcelable

@Parcelize
data class Avatar(
    val icon: String,
    val id: Int,
    val name: String,
    val sort_order: Int,
    val status: String
) : Parcelable