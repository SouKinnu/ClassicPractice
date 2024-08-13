package com.song.httplibrary.data

/**
 * @Author      : SongJin Yu
 * @Email       : kinnusou@gmail.com
 * @Date        : on 2024/5/17 21:17.
 * @Description :
 */

/**
 * @property userId             用户的唯一标识符。
 * @property cartItems          购物车中所有店铺的商品列表。
 * @property cartSummary        购物车的总计信息。
 */
data class Cart(
    val userId: String,
    val cartItems: List<CartItems>,
    val cartSummary: CartSummary
)

/**
 * @property shopId             店铺的唯一标识符。
 * @property isShopSelected     店铺是否被选中。
 * @property shopName           店铺名称。
 * @property shopLogoUrl        店铺Logo的链接。
 * @property items              店铺中的商品列表。
 */
data class CartItems(
    val shopId: String,
    val shopName: String,
    val isShopSelected: Boolean,
    val shopLogoUrl: String,
    val items: List<Item>
)

/**
 * @property itemId             商品的唯一标识符。
 * @property itemName           商品名称。
 * @property itemImageUrl       商品图片的链接。
 * @property itemPrice          商品单价。
 * @property itemQuantity       购买的商品数量。
 * @property itemTotalPrice     商品总价（单价乘以数量）。
 * @property itemSku            商品的SKU或规格。
 * @property isItemSelected     商品是否被选中。
 * @property itemDiscount       商品折扣。
 * @property finalPrice         商品的最终价格（总价减去折扣）。
 * @property itemLink           商品的链接。
 */
data class Item(
    val itemId: String,
    val itemName: String,
    val itemImageUrl: String,
    val itemPrice: Double,
    val itemQuantity: Int,
    val itemTotalPrice: Double,
    val itemSku: String,
    val isItemSelected: Boolean,
    val itemDiscount: Double,
    val finalPrice: Double,
    val itemLink: String
)

/**
 * @property totalItems         购物车中商品的总数量。
 * @property totalPrice         购物车中所有商品的总价。
 * @property totalDiscount      所有商品的折扣总额。
 * @property finalPrice         购物车中所有商品的最终总价（总价减去折扣）。
 * @property isAllSelected      是否所有商品都被选中。
 */
data class CartSummary(
    val totalItems: Int,
    val totalPrice: Double,
    val totalDiscount: Double,
    val finalPrice: Double,
    val isAllSelected: Boolean
)