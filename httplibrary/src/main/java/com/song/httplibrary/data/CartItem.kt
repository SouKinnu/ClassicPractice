package com.song.httplibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CartItem(
    val type: CartType, // 用于区分店铺和商品
    var isItemSelected: Boolean? = null,
    val data: @RawValue Any,
    val shopName: String
) : Parcelable

enum class CartType {
    SHOP, ITEM
}