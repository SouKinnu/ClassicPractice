package com.song.httplibrary.data

data class CartItem(
    val type: ItemType, // 用于区分店铺和商品
    val shopId: String? = null,
    val shopName: String? = null,
    val shopLogoUrl: String? = null,
    val itemId: String? = null,
    val itemName: String? = null,
    val itemImageUrl: String? = null,
    val itemPrice: Double? = null,
    val itemQuantity: Int? = null,
    val itemTotalPrice: Double? = null,
    val itemSku: String? = null,
    var isItemSelected: Boolean? = null,
    val itemDiscount: Double? = null,
    val finalPrice: Double? = null,
    val itemLink: String? = null
)

enum class ItemType {
    SHOP, ITEM
}