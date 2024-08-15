package com.song.httplibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024-08-15
 * @Description : 用于表示购物车中的项，可以是店铺或商品。实现了 Parcelable 接口以便于在 Android 的组件之间传递数据。
 */
@Parcelize
data class CartItem(
    /**
     * 类型标识符，用于区分项的类型：店铺或商品。
     * @see CartType
     */
    val type: CartType,

    /**
     * 表示该项是否被选中。可能为 null 表示未设置选择状态。
     */
    var isItemSelected: Boolean = true,

    /**
     * 存储项的具体数据。使用 @RawValue 注解允许存储任意类型的数据。
     */
    val data: @RawValue Any,

    /**
     * 店铺的唯一标识符，用于区分不同的店铺。
     */
    val shopId: String
) : Parcelable

/**
 * @Author      : Song Jin yu
 * @Email       : kinnusou@gmail.com
 * @Date        : 2024-08-15
 * @Description : 定义购物车项的类型。
 */
enum class CartType {
    /** 店铺类型 */
    SHOP,

    /** 商品类型 */
    ITEM
}